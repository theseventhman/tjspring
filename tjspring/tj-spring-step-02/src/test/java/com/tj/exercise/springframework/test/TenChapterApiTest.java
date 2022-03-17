package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.context.support.ClassPathXmlApplicationContext;
import com.tj.exercise.springframework.test.bean.TenChapterUserService;
import com.tj.exercise.springframework.test.bean.UserService;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: tj
 * @Date: 2022/3/17 21:46
 */
public class TenChapterApiTest {

    @Test
    public void test_prototype(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:TenChapterSpring.xml");
        applicationContext.registerShutDownHook();

        TenChapterUserService userService01 = applicationContext.getBean("userService", TenChapterUserService.class);
        TenChapterUserService userService02 = applicationContext.getBean("userService", TenChapterUserService.class);

        System.out.println(userService01);
        System.out.println(userService02);

        System.out.println(userService01 +" 十六进制哈希: " + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void test_factory_bean(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:TenChapterSpring.xml");
        applicationContext.registerShutDownHook();
        TenChapterUserService userService = applicationContext.getBean("userService",TenChapterUserService.class);
        System.out.println("测试结果: " + userService.queryUserInfo());

    }
}
