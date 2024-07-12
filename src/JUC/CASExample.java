package JUC;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CASExample {
    private static final Unsafe unsafe;
    private static final long valueOffset;

    private volatile int value;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);

            valueOffset = unsafe.objectFieldOffset(CASExample.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public CASExample(int initialValue) {
        this.value = initialValue;
    }

    public boolean compareAndSwapValue(int expectedValue, int newValue) {
        return unsafe.compareAndSwapInt(this, valueOffset, expectedValue, newValue);
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        CASExample casExample = new CASExample(0);

        System.out.println("Initial Value: " + casExample.getValue());

        boolean success = casExample.compareAndSwapValue(0, 42);
        System.out.println("CAS success: " + success);
        System.out.println("Updated Value: " + casExample.getValue());

        success = casExample.compareAndSwapValue(0, 43);
        System.out.println("CAS success: " + success);
        System.out.println("Updated Value: " + casExample.getValue());
    }
}
