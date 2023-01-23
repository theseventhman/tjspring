package com.tj.exercise.springframework.test;

import cn.hutool.core.bean.BeanException;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.aop.AdvisedSupport;
import com.tj.exercise.springframework.aop.ClassFilter;
import com.tj.exercise.springframework.aop.MethodMatcher;
import com.tj.exercise.springframework.aop.TargetSource;
import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.tj.exercise.springframework.aop.framework.ProxyFactory;
import com.tj.exercise.springframework.aop.framework.ReflectiveMethodInvocation;
import com.tj.exercise.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import com.tj.exercise.springframework.context.support.ClassPathXmlApplicationContext;
import com.tj.exercise.springframework.test.bean.*;
import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tj
 * @Date: 2022/5/17 23:21
 */
public class ApiTest {

    @Test
    public void test_circular(){
       ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
       Husband husband = applicationContext.getBean("husband", Husband.class);
       Wife wife = applicationContext.getBean("wife",Wife.class);
        System.out.println("老公的媳妇: " + husband.queryWife());
        System.out.println("媳妇的老公: " + wife.queryHusband());
    }








}
