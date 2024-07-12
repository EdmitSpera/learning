package JUC;

import java.sql.Time;
import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class poolRepeat {
    private int coreThread;     // 核心线程数
    private int poolThread;     // 最大线程数
    private int queueLength;    // 队列容量

    private Worker[] pool;
    private QueueMq queue;
    // 同步锁和监听器
    private Lock lock = new ReentrantLock();
    private Condition isEmpty = lock.newCondition();

    public Worker[] getPool() {
        return pool;
    }

    // 内部消息队列
    class QueueMq {
        private int length;     // 队列容量
        private ArrayDeque<Runnable> queue;

        public QueueMq(int length) {
            this.length = length;
            queue = new ArrayDeque<>();
        }

        public boolean add(Runnable r) {
            if (queue.size() >= length && !extraWorker()) { // 已经满了
                return false;   // 拒绝策略 -> 丢弃任务
            }
            queue.addFirst(r);
            isEmpty.signal();
            return true;
        }

        public Runnable remove() {
            return queue.removeLast();
        }
    }

    public boolean extraWorker(){
        for(int i = coreThread; i < poolThread; i++){
            if(pool[i] == null){
                pool[i] = new Worker();
                pool[i].start();
                return true;
            }
        }
        return false;
    }


    class Worker extends Thread {
        @Override
        public void run() {
            // 每一个Work轮询的方式获取队列中的任务,成功则执行
            while (true) {
                Runnable task;
                lock.lock();
                try {    // 获取到任务的逻辑
                    // 任务队列为空，则阻塞
                    while (queue.queue.isEmpty()) {
                        try {
                            isEmpty.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    task = queue.remove();
                } finally {
                    lock.unlock();
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }

    // 构造方法
    public poolRepeat(int coreThread, int poolThread, int queueLength) {
        this.coreThread = coreThread;
        this.poolThread = poolThread;
        this.queueLength = queueLength;
        this.queue = new QueueMq(queueLength);
        this.pool = new Worker[poolThread];

        // 线程池初始化核心线程
        for (int i = 0; i < coreThread; i++) {
            pool[i] = new Worker();
            pool[i].start();
        }
    }

    public void execute(Runnable task) {
        lock.lock();
        try {
            if(!queue.add(task)){
                System.out.println("超过最大线程数，任务" + task.getClass().getName() + "被丢弃");
            }
        } finally {
            lock.unlock();
        }
    }

    // 主方法，测试线程池
    public static void main(String[] args) throws InterruptedException {
        TaskA taskA = new TaskA();
        TaskB taskB = new TaskB();
        Thread thread = new Thread(taskA);
        poolRepeat pool = new poolRepeat(3, 5, 3);
        Worker[] workerBefore = pool.getPool();
        for (Worker worker : workerBefore) {
            if(worker != null)
                System.out.print(worker.toString() + " ");
        }
        pool.execute(taskA);
        pool.execute(taskB);
        pool.execute(taskB);
        pool.execute(taskB);
        pool.execute(taskA);
        pool.execute(taskB);
        pool.execute(taskA);
        Worker[] workerAfter = pool.getPool();

        thread.sleep(5000);
        for (Worker worker: workerAfter){
            if(worker != null)
                System.out.print(worker.toString() + " ");
        }
    }

    // 任务A
    static class TaskA implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("TaskA start");
                Thread.sleep(3000);
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
                Thread.sleep(5000);
                System.out.println("TaskB finish");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
