package com.javadiscord.jdi.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ReflectiveLoader {
    private ReflectiveLoader() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static <T> T proxy(Object object, Class<T> interfaceClass) {
        InvocationHandler handler =
            (proxy, method, methodArgs) -> object.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .invoke(object, methodArgs);

        @SuppressWarnings("unchecked") T proxyInstance =
            (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[] {interfaceClass},
                handler
            );

        return proxyInstance;
    }
}
