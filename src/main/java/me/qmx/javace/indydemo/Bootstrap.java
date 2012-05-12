package me.qmx.javace.indydemo;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.*;

import static java.lang.invoke.MethodType.methodType;
import static me.qmx.jitescript.util.CodegenUtils.p;

public class Bootstrap {

    public static final Handle BOOTSTRAP = new Handle(Opcodes.H_INVOKESTATIC,
            p(Bootstrap.class), "bootstrap", methodType(CallSite.class,
            MethodHandles.Lookup.class, String.class, MethodType.class).toMethodDescriptorString());
    public static final Object[] BOOTSTRAP_ARGS = new Object[0];

    public static CallSite bootstrap(MethodHandles.Lookup lookup, String name, MethodType methodType) throws NoSuchMethodException, IllegalAccessException {
        if (name.equals("baba")) {
            MethodHandle helloBaba = lookup.findStatic(Car.class, "helloBaba", methodType.appendParameterTypes(String.class));
            return new ConstantCallSite(MethodHandles.insertArguments(helloBaba, 1, "from invokedynamic => "));
        } else if (name.equals("dyn:call:honk")) {
            String[] tokens = name.split(":");
            if (tokens.length == 3) {
                MethodHandle handle = lookup.findVirtual(Car.class, tokens[2], methodType);
                return new ConstantCallSite(handle);
            }
        }
        return null;
    }
}
