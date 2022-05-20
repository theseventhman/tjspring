package com.tj.exercise.springframework.aop.aspectj;

import com.tj.exercise.springframework.aop.Pointcut;
import com.tj.exercise.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @Author: tj
 * @Date: 2022/5/8 23:23
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 具体的拦截方法
     */
    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * 表达式
     */
    private String expression;

    @Override
    public Pointcut getPointcut() {
        if(null == pointcut){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
