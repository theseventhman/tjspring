package com.tj.exercise.springframework.beans.factory.config;

import com.tj.exercise.springframework.context.event.ApplicationEventMulticaster;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:17
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObjects);
}
