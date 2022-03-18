package com.tj.exercise.springframework.test.event;

import com.tj.exercise.springframework.context.ApplicationListener;
import com.tj.exercise.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: tj
 * @Date: 2022/3/18 11:57
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件: " + this.getClass().getName());
    }
}
