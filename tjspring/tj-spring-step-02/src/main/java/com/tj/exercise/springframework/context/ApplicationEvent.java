package com.tj.exercise.springframework.context;

import java.util.EventObject;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:37
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {super(source);}
}
