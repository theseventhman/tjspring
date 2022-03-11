package com.tj.exercise.springframework.beans.factory.support;

import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: tj
 * @Date: 2022/3/11 11:20
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
