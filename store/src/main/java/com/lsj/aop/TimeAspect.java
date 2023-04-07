package com.lsj.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component//将当前类的对象创建使用维护交给spring容器来完成
@Aspect //将当前类标记为切面
public class TimeAspect {
    /*
     * @param pjp 表示连接点，目标方法的对象
     * @return
     * @Date 8:31 2023/3/25
     **/
    @Around("execution(* com.lsj.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("此方法耗时："+(end-start));
        return result;
    }



}
