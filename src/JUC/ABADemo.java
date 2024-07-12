package JUC;

import java.util.concurrent.atomic.AtomicReference;

public class ABADemo {
    private static AtomicReference<Integer> atomicInteger = new AtomicReference<>(100);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Integer value = atomicInteger.get();
            System.out.println("Thread 1 initial value: " + value);

            // 模拟 ABA 问题
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);

            System.out.println("Thread 1 final value: " + atomicInteger.get());
        });

        Thread t2 = new Thread(() -> {
            try {
                // 确保 t1 先执行
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean success = atomicInteger.compareAndSet(100, 200);
            System.out.println("Thread 2 success: " + success);
            System.out.println("Thread 2 final value: " + atomicInteger.get());
        });

        t1.start();
        t2.start();
    }
}
