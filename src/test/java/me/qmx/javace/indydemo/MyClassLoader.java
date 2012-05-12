package me.qmx.javace.indydemo;

class MyClassLoader extends ClassLoader {
    public Class<?> define(String className, byte[] bytecode) {
        return super.defineClass(className, bytecode, 0, bytecode.length);
    }
}
