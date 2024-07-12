package JUC;

public class MemoryBarrierExample {
    private volatile boolean flag = false;
    private int counter = 0;

    public void writer() {
        counter++;      // 普通写操作
        flag = true;    // volatile 写操作
    }

    public void reader() {
        if (flag) {     // volatile 读操作
            System.out.println(counter);  // 保证读取到最新的 counter 值
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MemoryBarrierExample example = new MemoryBarrierExample();

        Thread writerThread = new Thread(example::writer);
        Thread readerThread = new Thread(example::reader);

        writerThread.start();
        readerThread.start();

        writerThread.join();
        readerThread.join();
    }
}
