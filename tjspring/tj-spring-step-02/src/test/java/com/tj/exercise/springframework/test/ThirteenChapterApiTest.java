package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.aop.AdvisedSupport;
import com.tj.exercise.springframework.aop.ClassFilter;
import com.tj.exercise.springframework.aop.MethodMatcher;
import com.tj.exercise.springframework.aop.TargetSource;
import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.tj.exercise.springframework.aop.framework.ProxyFactory;
import com.tj.exercise.springframework.aop.framework.ReflectiveMethodInvocation;
import com.tj.exercise.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.tj.exercise.springframework.context.support.ClassPathXmlApplicationContext;
import com.tj.exercise.springframework.test.bean.IUserService;
import com.tj.exercise.springframework.test.bean.ThirteenChapterUserService;
import com.tj.exercise.springframework.test.bean.UserServiceBeforeAdvice;
import com.tj.exercise.springframework.test.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: tj
 * @Date: 2022/5/17 23:21
 */
public class ThirteenChapterApiTest {





}
