package com.tj.exercise.springframework.test.bean;

import com.tj.exercise.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: tj
 * @Date: 2022/5/13 22:06
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法: " + method.getName());
    }
}
