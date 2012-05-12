package me.qmx.javace.indydemo;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    @Test
    public void testMethodHandles() throws Throwable, IllegalAccessException {
        Car car = new Car();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle honk = lookup.findVirtual(Car.class, "honk", MethodType.methodType(void.class));
        honk.invoke(car);
    }
}
