package JUC;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class counter {
    public static int counter = 0;
//    public static Object lock = new Object();
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new TaskA());
        pool.execute(new TaskB());

    }

    // 2.使用ReentrantLock和Condition实现同步
    static class TaskA implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (counter >= 200) {
                        lock.notifyAll();
                        break;
                    }
                    counter++;
                    System.out.println(counter);
                    lock.notifyAll();   // 唤醒在该对象上等待的单个线程 唤醒的线程会重新竞争对象的监视器锁
                    try {
                        lock.wait();    // 释放当前获得的锁，并进入等待唤醒的状态
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }



    static class TaskB implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (counter >= 200) {
                        lock.notifyAll();
                        break;
                    }
                    counter++;
                    System.out.println(counter);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

//    static class TaskC implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                synchronized (lock) {
//                    if (counter >= 100) {
//                        lock.notifyAll();
//                        break;
//                    }
//                    System.out.print("Thread C: " + counter + " ");
//                    counter++;
//                    System.out.println(counter);
//                    lock.notifyAll();
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }

    // 1.使用synchronized和Object类监视器实现同步
//    static class TaskA implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                synchronized (lock) {
//                    if (counter >= 100) {
//                        lock.notifyAll();
//                        break;
//                    }
//                    System.out.print("Thread A: " + counter + " ");
//                    counter++;
//                    System.out.println(counter);
//                    lock.notifyAll();   // 唤醒在该对象上等待的单个线程 唤醒的线程会重新竞争对象的监视器锁
//                    try {
//                        lock.wait();    // 释放当前获得的锁，并进入等待唤醒的状态
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }
//
//    static class TaskB implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                synchronized (lock) {
//                    if (counter >= 100) {
//                        lock.notifyAll();
//                        break;
//                    }
//                    System.out.print("Thread B: " + counter + " ");
//                    counter++;
//                    System.out.println(counter);
//                    lock.notifyAll();
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }
//
//    static class TaskC implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                synchronized (lock) {
//                    if (counter >= 100) {
//                        lock.notifyAll();
//                        break;
//                    }
//                    System.out.print("Thread C: " + counter + " ");
//                    counter++;
//                    System.out.println(counter);
//                    lock.notifyAll();
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }
}
