package me.qmx.javace.indydemo;

import me.qmx.jitescript.CodeBlock;
import me.qmx.jitescript.JDKVersion;
import me.qmx.jitescript.JiteClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;

public class InvokeDynamicTest {

    @Test
    public void testInvokeDynamicCall() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        JiteClass jiteClass = new JiteClass("CarInvoker") {{
            defineDefaultConstructor();
            defineMethod("callHonk", ACC_STATIC | ACC_PUBLIC, sig(void.class), new CodeBlock(){{
                // new instance
                newobj(p(Car.class));
                dup();
                // calls the constructor
                invokespecial(p(Car.class), "<init>", sig(void.class));
                // invokes the "honk" method
                invokedynamic("baba", sig(void.class, Object.class), Bootstrap.BOOTSTRAP, Bootstrap.BOOTSTRAP_ARGS);

                voidreturn();
            }});
        }};
        byte[] bytes = jiteClass.toBytes(JDKVersion.V1_7);
        Class<?> carInvokerClass = new MyClassLoader().define(jiteClass.getClassName(), bytes);
        Method callHonk = carInvokerClass.getDeclaredMethod("callHonk");
        callHonk.invoke(null);
        callHonk.invoke(null);
        callHonk.invoke(null);
    }
}
