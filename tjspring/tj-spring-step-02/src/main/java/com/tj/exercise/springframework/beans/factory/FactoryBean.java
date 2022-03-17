package com.tj.exercise.springframework.beans.factory;

/**
 * @Author: tj
 * @Date: 2022/3/17 21:15
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
