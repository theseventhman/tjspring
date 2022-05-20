package com.tj.exercise.springframework.aop;

/**
 * @Author: tj
 * @Date: 2022/5/8 23:21
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that derives this advisor
     * @return
     */
    Pointcut getPointcut();
}
