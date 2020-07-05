package com.vidor;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtil {
    public static void start(Method method, Object... objects) {
        if (objects==null || objects.length ==0){
            System.out.println(method.getName() + "方法开始执行");
        } else {
            System.out.println(method.getName() + "方法开始执行，参数是：" + Arrays.asList(objects));
        }

    }

    public static void stop(Method method, Object... objects) {
        if (objects==null || objects.length ==0){
            System.out.println(method.getName() + "方法执行完毕");
        } else {
            System.out.println(method.getName() + "方法执行完毕，参数是：" + Arrays.asList(objects));
        }
    }

    public static void logException(Method method, Exception e) {
        System.out.println(method.getName() + "方法出现异常：" + e.getMessage());
    }

    public static void end(Method method) {
        System.out.println(method.getName() + "方法执行结束了......");
    }
}
