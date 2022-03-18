package com.tj.exercise.springframework.context.event;

import com.tj.exercise.springframework.context.ApplicationContext;
import com.tj.exercise.springframework.context.ApplicationEvent;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:41
 */
public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
