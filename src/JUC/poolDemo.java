package JUC;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class poolDemo {
    public static void main(String[] args) {
        // 创建线程池的方式
        // 1. 使用ThreadPoolExecutor
        // 2. 使用Executor中的静态方法实例化ThreadPoolExecutor对象
        //    Executor可创建多种不同类型的线程池对象：
        //          FixedThreadPool         固定线程数量
        //          SingleThreadExecutor    单一线程的线程池        FixedThreadPool 和 SingleThreadExecutor:使用的是无界的 LinkedBlockingQueue，任务队列最大长度为 Integer.MAX_VALUE,可能堆积大量的请求，从而导致 OOM。
        //          CachedThreadPool        根据实际情况动态调整线程的数量  核心线程数为0 最大线程数不限制 适合短时间大量短任务的场景    使用的是同步队列 SynchronousQueue, 允许创建的线程数量为 Integer.MAX_VALUE ，如果任务数量过多且执行速度较慢，可能会创建大量的线程，从而导致 OOM。
        //          ScheduledThreadPool     设定周期，定期执行任务
//        ThreadPoolExecutor.AbortPolicy
    }
}
