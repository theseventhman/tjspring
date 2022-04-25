package com.tj.exercise.springframework.test;

import com.sun.org.apache.xml.internal.utils.res.XResources_ja_JP_HI;
import com.tj.exercise.springframework.aop.AdvisedSupport;
import com.tj.exercise.springframework.aop.MethodMatcher;
import com.tj.exercise.springframework.aop.TargetSource;
import com.tj.exercise.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.tj.exercise.springframework.aop.framework.Cglib2AopProxy;
import com.tj.exercise.springframework.aop.framework.JdkDynamicAopProxy;
import com.tj.exercise.springframework.aop.framework.ReflectiveMethodInvocation;
import com.tj.exercise.springframework.test.bean.IUserService;
import com.tj.exercise.springframework.test.bean.TwelveChapterUserService;
import com.tj.exercise.springframework.test.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

    @Test
    public void test_proxy_class(){
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] {IUserService.class}, (proxy, method, args) -> "你被代理了！" );
        String result = userService.queryUserInfo();
        System.out.println("测试结果: " + result);
    }

    @Test
    public void test_proxy_method() {
        Object targetObj = new TwelveChapterUserService();

        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.tj.exercise.springframework.test.bean.IUserService.*(..))");


        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(methodMatcher.matches(method,targetObj.getClass())) {
                    MethodInterceptor methodInterceptor = invocation ->{
                        long start = System.currentTimeMillis();
                        try{
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称: " + invocation.getMethod().getName());
                            System.out.println("方法耗时: " +(System.currentTimeMillis() - start) +"ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };

                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj,method,args));
                }
                return method.invoke(targetObj,args);
            }
        });

        String result = proxy.queryUserInfo();
        System.out.println("测试结果: " +result);
    }
}
