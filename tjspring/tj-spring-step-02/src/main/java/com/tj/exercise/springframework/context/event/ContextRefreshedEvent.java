package com.tj.exercise.springframework.context.event;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:43
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
