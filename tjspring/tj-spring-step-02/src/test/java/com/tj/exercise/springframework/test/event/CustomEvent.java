package com.tj.exercise.springframework.test.event;

import com.tj.exercise.springframework.context.event.ApplicationContextEvent;

/**
 * @Author: tj
 * @Date: 2022/3/18 11:51
 */
public class CustomEvent extends ApplicationContextEvent {

    private long id;
    private String message;

    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
