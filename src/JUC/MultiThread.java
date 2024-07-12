package JUC;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

        // 结构：一个 Java 程序的运行是 main 线程和多个其他线程同时运行
        //[1] main
        //[9] Reference Handler
        //[10] Finalizer
        //[11] Signal Dispatcher
        //[12] Attach Listener
        //[19] Common-Cleaner
        //[20] Monitor Ctrl-Break
        //[21] Notification Thread

        /*
        /在 JDK 1.2 及以后，Java 线程改为基于原生线程（Native Threads）实现，也就是
        说 JVM 直接使用操作系统原生的内核级线程（内核线程）来实现 Java 线程，由操作系统内核进行线程的调度和管理。
         */
    }
}
