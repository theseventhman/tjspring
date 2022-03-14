package com.tj.exercise.springframework.beans.factory.support;

import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: tj
 * @Date: 2022/3/11 12:53
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
