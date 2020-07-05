package com.vidor;

import com.vidor.action.ICalculator;
import com.vidor.mode.Calculator;
import com.vidor.proxy.MyInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 使用jdk动态代理完成增加日志功能:动态生成代理类
 */
public class MainTest {

    public static void main(String[] args) {
        ICalculator proxy = (ICalculator) MainTest.createProxy(new Calculator());
        proxy.div(1, 0);
    }

    /**
     * 公共jdk动态代理对象生成
     *
     * @return
     */
    public static Object createProxy(Object needProxy) {

        ClassLoader classLoader = needProxy.getClass().getClassLoader();
        Class<?>[] interfaces = needProxy.getClass().getInterfaces();

        // 传入被代理的对象
        InvocationHandler handler = new MyInvocationHandler(needProxy);

        // 动态创建代理类
        Object o = Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println(o.getClass());

        return o;
    }
}
