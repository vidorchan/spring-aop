package com.vidor.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    // 被代理的对象
    Object target;

    public MyInvocationHandler( Object target) {
        this.target=target;
    }

    // 代理类的执行方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result=null;
        try {
            //LogUtil.before(method,args);
            result = method.invoke(target, args);
            //LogUtil.after(method,args);
        }
        catch (Exception ex){
            //LogUtil.afterException(ex);
        }
        finally {
            //LogUtil.afterEnd(result);
        }

        return result;
    }
}
