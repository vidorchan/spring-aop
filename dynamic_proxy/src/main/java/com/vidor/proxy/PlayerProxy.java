package com.vidor.proxy;

import com.vidor.LogUtil;
import com.vidor.action.Play;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PlayerProxy {

    public static Object createProxy(final Play play) {
        //被代理对象的类加载器
        ClassLoader loader = play.getClass().getClassLoader();
        //被代理对象的接口
        Class<?>[] interfaces = play.getClass().getInterfaces();
        //方法执行器，执行被代理对象的目标方法
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Object result = null;
            try{
                LogUtil.start(method, args);
                result = method.invoke(play, args);
                LogUtil.stop(method, args);
            } catch (Exception ex){
                LogUtil.logException(method, ex);
            } finally {
                LogUtil.end(method);
            }
            return result;
        };
        Object o = Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        System.out.println(o.getClass());
        return o;
    }
}
