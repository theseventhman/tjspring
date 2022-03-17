package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.context.support.ClassPathXmlApplicationContext;
import com.tj.exercise.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @Author: tj
 * @Date: 2022/3/17 12:26
 */
public class EightChapterApiTest {

    @Test
    public void test_xml(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutDownHook();

        UserService userService = applicationContext.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果: " +result);
    }

    @Test
    public void test_hook(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close!")));
    }
}
