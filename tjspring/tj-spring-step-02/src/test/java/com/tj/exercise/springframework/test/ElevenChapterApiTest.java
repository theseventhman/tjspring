package com.tj.exercise.springframework.test;

import com.tj.exercise.springframework.context.support.ClassPathXmlApplicationContext;
import com.tj.exercise.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * @Author: tj
 * @Date: 2022/3/18 12:00
 */
public class ElevenChapterApiTest {

    @Test
    public void test_event(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ElevenChapterSpring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext,1019129009086764L, "成功了！"));

        applicationContext.registerShutDownHook();
    }
}
