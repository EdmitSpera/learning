package 并发;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo {
    // 公平锁
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        for (int k = 0; k < 5; k++) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            // 创建线程
            Thread[] threads = new Thread[10000];
            for (int i = 0; i < 10000; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 2; j++) {
                        lock.lock();
                        try {
//                        System.out.println("当前线程：" + Thread.currentThread().getName());
                        } finally {
                            lock.unlock();
                        }
                    }
                });
                threads[i].start();
            }

            // 等待所有线程执行完毕
            for (Thread thread : threads) {
                thread.join();
            }

            // 记录结束时间
            long endTime = System.currentTimeMillis();

            // 计算并打印运行时间
            long duration = endTime - startTime;
            System.out.println("程序运行时间：" + duration + " 毫秒");
        }

    }
}
