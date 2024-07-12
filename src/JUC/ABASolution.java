package JUC;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABASolution {
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int[] stampHolder = new int[1];
            Integer value = atomicStampedReference.get(stampHolder);
            System.out.println("Thread 1 initial value: " + value + ", initial stamp: " + stampHolder[0]);

            // 模拟 ABA 问题
            atomicStampedReference.compareAndSet(100, 101, stampHolder[0], stampHolder[0] + 1);
            atomicStampedReference.compareAndSet(101, 100, stampHolder[0] + 1, stampHolder[0] + 2);

            System.out.println("Thread 1 final value: " + atomicStampedReference.get(stampHolder) + ", final stamp: " + stampHolder[0]);
        });

        Thread t2 = new Thread(() -> {
            try {
                // 确保 t1 先执行
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int[] stampHolder = new int[1];
            Integer value = atomicStampedReference.get(stampHolder);
            boolean success = atomicStampedReference.compareAndSet(100, 200, stampHolder[0], stampHolder[0] + 1);
            System.out.println("Thread 2 success: " + success);
            System.out.println("Thread 2 final value: " + atomicStampedReference.get(stampHolder) + ", final stamp: " + stampHolder[0]);
        });

        t1.start();
        t2.start();
    }
}
