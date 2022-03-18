package com.tj.exercise.springframework.context;

/**
 * @Author: tj
 * @Date: 2022/3/18 11:11
 */
public interface ApplicationEventPublisher {

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandleEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
