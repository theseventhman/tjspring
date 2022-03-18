package com.tj.exercise.springframework.context.event;

import com.tj.exercise.springframework.context.ApplicationEvent;
import com.tj.exercise.springframework.context.ApplicationListener;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:44
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
