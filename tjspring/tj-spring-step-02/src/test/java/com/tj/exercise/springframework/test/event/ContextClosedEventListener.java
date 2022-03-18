package com.tj.exercise.springframework.test.event;

import com.tj.exercise.springframework.context.ApplicationListener;
import com.tj.exercise.springframework.context.event.ContextClosedEvent;

/**
 * @Author: tj
 * @Date: 2022/3/18 11:56
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件: " +this.getClass().getName());
    }
}
