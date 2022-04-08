package com.tj.exercise.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: tj
 * @Date: 2022/3/22 21:45
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
