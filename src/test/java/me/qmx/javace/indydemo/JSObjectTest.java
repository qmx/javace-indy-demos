package me.qmx.javace.indydemo;

import me.qmx.jitescript.JDKVersion;
import me.qmx.jitescript.JiteClass;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class JSObjectTest {

    private JSObject obj;

    @Before
    public void setUp() throws Exception {
        obj = new JSObject();
        obj.getProperties().put("honk", new HashMap<String, Object>() {{
            put("call", Honk.class);
        }});
    }

    @Test
    public void testJSCall() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class honkImplementation = (Class) obj.getProperties().get("honk").get("call");
        honkImplementation.getDeclaredMethod("honk").invoke(null);
    }

    @Test
    public void testJSInvokeDynamicCall() {
        JiteClass jiteClass = new JiteClass("JavaScriptInvoker") {{

        }};
        byte[] bytes = jiteClass.toBytes(JDKVersion.V1_7);
        Class<?> invoker = new MyClassLoader().define(jiteClass.getClassName(), bytes);

    }
}
