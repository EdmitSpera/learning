package JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class parll {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.execute(new createTask());
        pool.execute(new createTask());
        pool.execute(new createTask());
        pool.execute(new createTask());
        pool.execute(new createTask());
    }

    static class createTask implements Runnable{
        public void run(){
            parll instance = parll.getInstance();
            System.out.println(instance);
        }
    }

    private static volatile parll singleton;
    private static Lock lock = new ReentrantLock();

    private parll() {
    }

    public static parll getInstance(){
        // 懒汉模式
        if(singleton == null){
            // 双重锁机制
            lock.lock();
            if(singleton == null){
                singleton = new parll();
            }
            lock.unlock();
        }
        return singleton;
    }
}
