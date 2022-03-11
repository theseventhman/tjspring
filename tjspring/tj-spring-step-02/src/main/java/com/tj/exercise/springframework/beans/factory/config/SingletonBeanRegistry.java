package com.tj.exercise.springframework.beans.factory.config;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:17
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
