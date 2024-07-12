package JUC;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class FenceExample {
    private static final Unsafe unsafe;

    private volatile int value;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getValue() {
        // Ensure that the read operation happens after any prior reads.
        unsafe.loadFence();
        return value;
    }

    public void setValue(int value) {
        // Ensure that all previous writes happen before this write.
        this.value = value;
        unsafe.storeFence();
    }

    public void setAndGet(int value) {
        // Full fence to ensure all previous reads and writes are completed
        // before the current operation, and all subsequent reads and writes
        // happen after this operation.
        unsafe.fullFence();
        this.value = value;
        unsafe.fullFence();
        System.out.println("Value set to: " + this.value);
    }

    public static void main(String[] args) {
        FenceExample example = new FenceExample();
        example.setValue(42);
        System.out.println("Value after setValue: " + example.getValue());

        example.setAndGet(99);
        System.out.println("Value after setAndGet: " + example.getValue());
    }
}
