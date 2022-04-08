package com.tj.exercise.springframework.test.bean;

import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: tj
 * @Date: 2022/4/8 22:13
 */
public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return methodInvocation.proceed();
        } finally {
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称: " + methodInvocation.getMethod());
            System.out.println("方法耗时: " +(System.currentTimeMillis() - start) +"ms");
            System.out.println("监控 - End\r\n");
        }
    }
}
