package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.tj.exercise.springframework.test.bean.UserService;
import org.junit.Test;

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

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        UserService singleUserService = (UserService) beanFactory.getSingleton("userService");
        singleUserService.queryUserInfo();
    }
}
