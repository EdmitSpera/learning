package 并发;

import java.util.concurrent.atomic.AtomicInteger;

public class CASExample {

    // 创建一个AtomicInteger实例
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        // 启动多个线程进行CAS操作
        for (int i = 0; i < 10; i++) {
            new Thread(new CASRunnable()).start();
        }
    }

    // 定义一个Runnable类，执行CAS操作
    static class CASRunnable implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                int expectedValue;
                int newValue;
                boolean updated;
                do {
                    expectedValue = atomicInteger.get();
                    newValue = expectedValue + 1;
                    updated = atomicInteger.compareAndSet(expectedValue, newValue);
                    // 打印调试信息
                    System.out.println(Thread.currentThread().getName() + " - Expected: " + expectedValue + ", New: " + newValue + ", Updated: " + updated);
                } while (!updated);
            }
        }
    }
}

