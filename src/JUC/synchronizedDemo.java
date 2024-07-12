package JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class synchronizedDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.execute(new TaskA());
        pool.execute(new TaskB());
        pool.execute(new TaskC());
        pool.execute(new TaskD());
    }

    static int counter = 1;
    static int matexA = 1;
    static int matexB = 0;
    static int matexC = 0;
    static int matexD = 0;

    // 可使用自带的
//    static final Semaphore matexA = new Semaphore(1);
//    static final Semaphore matexB = new Semaphore(0);
//    static final Semaphore matexC = new Semaphore(0);
//    static final Semaphore matexD = new Semaphore(0);

    static boolean P(String op) {
        switch (op) {
            case "A":
                if (matexA == 0) {
                    return false;
                }
                matexA--;
                return true;

            case "B":
                if (matexB == 0) {
                    return false;
                }
                matexB--;
                return true;

            case "C":
                if (matexC == 0) {
                    return false;
                }
                matexC--;
                return true;

            case "D":
                if (matexD == 0) {
                    return false;
                }
                matexD--;
                return true;

            default:
                return false; // 默认情况下返回 false 或者根据需要处理
        }
    }

    static boolean V(String op) {
        switch (op) {
            case "A":
                if (matexA == 1) {
                    return false;
                }
                matexA++;
                return true;

            case "B":
                if (matexB == 1) {
                    return false;
                }
                matexB++;
                return true;

            case "C":
                if (matexC == 1) {
                    return false;
                }
                matexC++;
                return true;

            case "D":
                if (matexD == 1) {
                    return false;
                }
                matexD++;
                return true;

            default:
                return false; // 默认情况下返回 false 或者根据需要处理
        }
    }

    static class TaskA implements Runnable {

        @Override
        public void run() {
            while (counter <= 120) {
                // 自旋锁
                while (!P("A")) {

                }
                System.out.println(counter + "A");
                counter++;

                // 自旋释放
                while (!V("B")) {

                }
            }
        }
    }

    static class TaskB implements Runnable {

        @Override
        public void run() {
            while (counter <= 120) {
                // 自旋锁
                while (!P("B")) {

                }

                System.out.println(counter + "B");
                counter++;

                // 自旋释放
                while (!V("C")) {

                }
            }
        }
    }

    static class TaskC implements Runnable {

        @Override
        public void run() {
            while (counter <= 120) {

                // 自旋锁
                while (!P("C")) {

                }
                System.out.println(counter + "C");
                counter++;

                // 自旋释放
                while (!V("D")) {

                }
            }
        }
    }

    static class TaskD implements Runnable {

        @Override
        public void run() {
            while (counter <= 120) {

                // 自旋锁
                while (!P("D")) {

                }
                System.out.println(counter + "D\n");
                counter++;

                // 自旋释放
                while (!V("A")) {

                }
            }
        }
    }
}
