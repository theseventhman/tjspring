package com.tj.exercise.springframework.aop;

/**
 * @Author: tj
 * @Date: 2022/3/22 21:43
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut
     *
     * @return the ClassFilter
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut
     *
     * @return the MethodMatcher
     */
    MethodMatcher getMethodMatcher();
}
