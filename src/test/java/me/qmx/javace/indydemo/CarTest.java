package me.qmx.javace.indydemo;

import me.qmx.jitescript.CodeBlock;
import me.qmx.jitescript.JiteClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;

public class CarTest {

    @Test
    public void testClassGeneration() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        JiteClass jiteClass = new JiteClass("CarInvoker") {{
            defineDefaultConstructor();
            defineMethod("callHonk", ACC_STATIC | ACC_PUBLIC, sig(void.class), new CodeBlock(){{
                // new instance
                newobj(p(Car.class));
                dup();
                // calls the constructor
                invokespecial(p(Car.class), "<init>", sig(void.class));
                // invokes the "honk" method
                invokevirtual(p(Car.class), "honk", sig(void.class));
                voidreturn();
            }});
        }};
        byte[] bytes = jiteClass.toBytes();
        Class<?> carInvokerClass = new MyClassLoader().define(jiteClass.getClassName(), bytes);
        Method callHonk = carInvokerClass.getDeclaredMethod("callHonk");
        callHonk.invoke(null);
    }

}
