package JUC;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class pool {
    // 最大线程数
    private final int maxThread;
    // 核心线程数
    private final int coreThread;
    // 队列长度
    private final int queueLength;
    // 消息队列
    private final ArrayDeque<Runnable> queue;
    // 线程池
    private final Worker[] workers;
    // 线程同步锁
    private final Lock lock = new ReentrantLock();
    // 队列非空条件
    private final Condition notEmpty = lock.newCondition();    // 不推荐使用Object类监听器 wait() notify() 多个生产者在并发情况下可能造成假死问题

    public pool(int maxThread, int coreThread, int queueLength) {
        this.maxThread = maxThread;
        this.coreThread = coreThread;
        this.queueLength = queueLength;

        queue = new ArrayDeque<>(queueLength);
        workers = new Worker[maxThread];

        // 初始化核心线程
        for (int i = 0; i < coreThread; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    class Worker extends Thread {
        // 消费者
        @Override
        public void run() {
            while (true) {  // 轮询的方式在任务队列中取任务
                Runnable task;
                lock.lock();
                try {
                    // 队列为空时等待
                    while (queue.isEmpty()) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            // 如果线程被中断，退出循环
                            return;
                        }
                    }
                    // 从队列中获取任务
                    task = queue.pollLast();
                } finally {
                    lock.unlock();
                }
                // 执行任务
                if (task != null) {
                    task.run();
                }
            }
        }
    }

    // 生产者
    public boolean execute(Runnable task) {
        lock.lock();
        try {
            // 如果队列已满，拒绝任务
            if (queue.size() == queueLength) {
                return false;
            }
            // 添加任务到队列，并唤醒等待的工作线程
            queue.addFirst(task);
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    // 主方法，测试线程池
    public static void main(String[] args) {
        pool pool = new pool(5, 3, 3);
        pool.execute(new TaskA());
        pool.execute(new TaskB());
    }

    // 任务A
    static class TaskA implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("TaskA start");
                Thread.sleep(5000);
                System.out.println("TaskA finish");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 任务B
    static class TaskB implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("TaskB start");
                Thread.sleep(10000);
                System.out.println("TaskB finish");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
