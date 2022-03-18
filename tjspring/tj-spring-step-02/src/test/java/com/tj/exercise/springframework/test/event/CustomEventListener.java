package com.tj.exercise.springframework.test.event;

import com.tj.exercise.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Author: tj
 * @Date: 2022/3/18 11:54
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到: " + event.getSource() +"消息;时间: " + new Date());
        System.out.println("消息: " + event.getId() + ":" +event.getMessage());
    }
}
