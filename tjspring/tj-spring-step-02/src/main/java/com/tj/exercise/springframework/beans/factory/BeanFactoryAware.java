package com.tj.exercise.springframework.beans.factory;

import com.tj.exercise.springframework.beans.BeansException;

import java.nio.file.Watchable;

/**
 * @Author: tj
 * @Date: 2022/3/17 13:23
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
