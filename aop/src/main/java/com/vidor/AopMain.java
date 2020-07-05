package com.vidor;

import com.vidor.service.ICalculator;
import com.vidor.service.impl.Calculator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@ComponentScan("com.vidor")
@EnableAspectJAutoProxy
public class AopMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopMain.class);
        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        ICalculator calculator = factory.getBean("calculator", ICalculator.class);
        ICalculator proxy = (ICalculator) AopMain.createProxy(calculator);
        proxy.div(4, 0);
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
