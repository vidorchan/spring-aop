package com.vidor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 环绕前置通知 -> 前置通知 -> 环绕后置通知  -> 环绕返回通知 -> 后置通知 -> 后置返回通知
 * <p>
 * 环绕前置通知 -> 前置通知 -> 环绕异常通知  -> 环绕返回通知 -> 后置通知 -> 后置异常通知
 */
@Aspect
@Component
public class LogUtil {

    @Pointcut("execution(* com.vidor.service..*.*(..))")
    public void pointCut() {
    }

    ;

    //**加了环绕通知，导致无法获的在后置返回AfterReturning中的returning的值：L52报空指针异常
    //因为需要指定方法返回值为Object，而不是void
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            System.out.println(pjp.getSignature().getName() + "的环绕前置通知");
            result = pjp.proceed();
            System.out.println(pjp.getSignature().getName() + "的环绕后置通知");
        } catch (Throwable throwable) {
            System.out.println(pjp.getSignature().getName() + "的环绕异常通知");
            throwable.printStackTrace();
        } finally {
            System.out.println(pjp.getSignature().getName() + "的环绕返回通知");
        }
        return result;
    }

    @Before("pointCut()")
    public void start(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "的前置通知");
    }

    @After("pointCut()")
    public void stop(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "的后置通知");
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        ex.printStackTrace();
        System.out.println(joinPoint.getSignature().getName() + "的后置异常通知，异常是：" + ex.toString());
    }

    @AfterReturning(value = "pointCut()", returning = "obj")
    public void end(JoinPoint joinPoint, Object obj) {
        System.out.println(joinPoint.getSignature().getName() + "的后置返回通知, 返回值是:" + obj.toString());
    }
}
