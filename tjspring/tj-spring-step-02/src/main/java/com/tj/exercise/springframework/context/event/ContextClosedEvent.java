package com.tj.exercise.springframework.context.event;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:42
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
