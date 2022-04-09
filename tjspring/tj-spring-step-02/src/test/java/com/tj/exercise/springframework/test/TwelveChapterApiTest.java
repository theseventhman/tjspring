package com.tj.exercise.springframework.test;

import com.sun.org.apache.xml.internal.utils.res.XResources_ja_JP_HI;
import com.tj.exercise.springframework.aop.AdvisedSupport;
import com.tj.exercise.springframework.aop.TargetSource;
import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.tj.exercise.springframework.aop.framework.Cglib2AopProxy;
import com.tj.exercise.springframework.aop.framework.JdkDynamicAopProxy;
import com.tj.exercise.springframework.test.bean.IUserService;
import com.tj.exercise.springframework.test.bean.TwelveChapterUserService;
import com.tj.exercise.springframework.test.bean.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: tj
 * @Date: 2022/4/8 22:19
 */
public class TwelveChapterApiTest {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.tj.exercise.springframework.test.bean.TwelveChapterUserService.*(..))");

        Class<TwelveChapterUserService> clazz = TwelveChapterUserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method,clazz));
    }

    @Test
    public void test_dynamic(){
        IUserService userService = new TwelveChapterUserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.tj.exercise.springframework.test.bean.IUserService.*(..))"));


        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        System.out.println("测试结果: " + proxy_jdk.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();

        System.out.println("测试结果: " + proxy_cglib.register("test"));

    }
}
