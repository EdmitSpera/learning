package JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class singleDesign {
    private volatile static singleDesign singleton;
    private static Lock lock = new ReentrantLock();

    private singleDesign(){     // 私有构造器，防止强引用实例化对象

    }

    public static singleDesign getInstance() {
        if(singleton == null) {
            lock.lock();
            if(singleton == null) {     // 双重判断,并发安全
                singleton = new singleDesign();
            }
            lock.unlock();
        }
        return singleton;
    }
}
