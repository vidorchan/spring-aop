package com.vidor;

import com.vidor.action.Play;
import com.vidor.mode.Player;
import com.vidor.proxy.PlayerProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Play play = (Play) PlayerProxy.createProxy(new Player());
        play.play();
    }

    /*
    JDK动态代理
     */
    public static Object createProxy(Object origin) {
        //被代理对象的类加载器
        ClassLoader loader = origin.getClass().getClassLoader();
        //被代理对象的接口
        Class<?>[] interfaces = origin.getClass().getInterfaces();
        //方法执行器，执行被代理对象的目标方法
        InvocationHandler invocationHandler = (proxy, method, args) -> method.invoke(origin, args);
        Object o = Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        System.out.println(o.getClass());
        return o;
    }
}
