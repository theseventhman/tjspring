package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.tj.exercise.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @Author: tj
 * @Date: 2022/3/11 11:32
 */
public class ApiTest {
    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService","test");
        userService.queryUserInfo();
    }

    @Test
    public void test_newInstance() throws IllegalAccessException,InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws  Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("test");
        System.out.println(userService);
    }

    @Test
    public void test_paramterTypes() throws  Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("test");
        System.out.println(userService);
    }

    @Test
    public void test_cglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object object = enhancer.create(new Class[]{String.class},new Object[]{"test"});
        System.out.println(object);
    }
}
