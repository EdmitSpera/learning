package JUC;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCollection {
    public static void main(String[] args) {
        // 方式1 Thread类 传入Runnable接口 实现类 重写Run方法
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter < 100) {
                    // 获取锁
                    synchronized (lock) {
                        // 进行类型检查
                        while (counter % 2 == 1) {
                            // 如果是奇数则阻塞
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        System.out.println("threadA:" + counter++);
                        // 释放锁
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter < 100) {
                    synchronized (lock) {
                        while (counter % 2 == 0) {
                            // 如果是偶数则阻塞
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        System.out.println("threadB:" + counter++);
                        lock.notifyAll();
                    }
                }
            }
        });
//        threadA.start();
//        threadB.start();

        // 创建线程的方式
        // 方式2 线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new TaskA());
        pool.execute(new TaskB());
    }

    // 实现交替循环打印0~100
    static Object lock = new Object();
    static int counter = 1;

    static class TaskA implements Runnable {
        @Override
        public void run() {
            while (counter <= 100) {
                // 获取锁
                synchronized (lock) {
                    // 进行类型检查
                    while (counter % 2 == 1) {
                        // 如果是奇数则阻塞
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println(counter + "Thread A:" + "A");
                    counter++;
                    // 释放锁
                    lock.notifyAll();
                }
            }
        }
    }

    static class TaskB implements Runnable {
        @Override
        public void run() {
            while (counter <= 100) {
                synchronized (lock) {
                    while (counter % 2 == 0) {
                        // 如果是偶数则阻塞
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println(counter + "Thread B:" + "B");
                    counter++;
                    lock.notifyAll();
                }
            }
        }
    }
}
