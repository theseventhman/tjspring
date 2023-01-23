package com.tj.exercise.springframework.test.bean;

import com.tj.exercise.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: tj
 * @Date: 2023/1/22 22:08
 */
public class SpouseAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面):" + method);
    }
}
