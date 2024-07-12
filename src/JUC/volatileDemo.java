package JUC;

public class volatileDemo {
    // 去掉 volatile 关键字
    private static volatile volatileDemo uniqueInstance;

    private int value;
    private boolean initialized;

    private volatileDemo() {
        // 模拟初始化操作的延迟
        value = 42;
        initialized = true;

    }

    public int getValue() {
        return value;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static volatileDemo getInstance() {
        if (uniqueInstance == null) {
            synchronized (volatileDemo.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new volatileDemo();
                }
            }
        }
        return uniqueInstance;
    }
}
