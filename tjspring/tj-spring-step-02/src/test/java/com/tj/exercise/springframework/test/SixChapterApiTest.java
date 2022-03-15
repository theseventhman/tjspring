package com.tj.exercise.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.PropertyValue;
import com.tj.exercise.springframework.beans.PropertyValues;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanReference;
import com.tj.exercise.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.tj.exercise.springframework.core.io.DefaultResourceLoader;
import com.tj.exercise.springframework.core.io.Resource;
import com.tj.exercise.springframework.test.bean.UserDao;
import com.tj.exercise.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: tj
 * @Date: 2022/3/15 11:19
 */
public class SixChapterApiTest {

    @Test
    public void test_BeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        String result = userService.queryUserInfo();
        System.out.println("测试结果: " +result);
    }

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException{
        Resource resource = resourceLoader.getResource("https://github.com/theseventhman/tjspring/step04/tjspring/tj-spring-step-02/src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
