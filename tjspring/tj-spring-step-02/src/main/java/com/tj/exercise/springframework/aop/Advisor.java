package com.tj.exercise.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @Author: tj
 * @Date: 2022/5/8 23:17
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
