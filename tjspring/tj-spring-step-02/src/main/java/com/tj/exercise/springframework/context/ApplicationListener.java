package com.tj.exercise.springframework.context;

import java.util.EventListener;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:46
 */
public interface ApplicationListener<E extends  ApplicationEvent> extends EventListener {
    /**
     * Handle an application event
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
