package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.junit.Test;

/**
 * @Author: tj
 * @Date: 2022/4/8 22:19
 */
public class TwelveChapterApiTest {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.tj.exercise.springframework.test.bean.TwelveChapterUserService.*(..))");
    }
}
