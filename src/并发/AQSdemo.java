package 并发;

import java.util.concurrent.locks.LockSupport;

public class AQSdemo {

    private static Thread threadA, threadB, threadC;

    private static final Object lock = new Object();
    private static volatile int state = 0; // 用于控制打印顺序

    public static void main(String[] args) {
        AQSdemo printer = new AQSdemo();

        threadA = new Thread(() -> {
            try {
                printer.printA(threadB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadB = new Thread(() -> {
            try {
                printer.printB(threadC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadC = new Thread(() -> {
            try {
                printer.printC(threadA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private void printA(Thread nextThread) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (state != 0) {
                    LockSupport.park();
                }
                System.out.print("A");
                state = 1;
                LockSupport.unpark(nextThread);
            }
        }
    }

    private void printB(Thread nextThread) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (state != 1) {
                    LockSupport.park();
                }
                System.out.print("B");
                state = 2;
                LockSupport.unpark(nextThread);
            }
        }
    }

    private void printC(Thread nextThread) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (state != 2) {
                    LockSupport.park();
                }
                System.out.print("C");
                state = 0;
                LockSupport.unpark(nextThread);
            }
        }
    }
}
