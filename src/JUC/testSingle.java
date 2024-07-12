package JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testSingle {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(100); // 增加线程数
        for (int i = 0; i < 100; i++) {
            pool.execute(new Task());
        }
        pool.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            volatileDemo instance = volatileDemo.getInstance();
            System.out.println(Thread.currentThread().getName() + ": " + instance + ", Value: " + instance.getValue() + ", Initialized: " + instance.isInitialized());
        }
    }
}
