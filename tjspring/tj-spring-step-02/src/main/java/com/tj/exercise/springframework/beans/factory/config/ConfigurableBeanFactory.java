package com.tj.exercise.springframework.beans.factory.config;

import com.tj.exercise.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:49
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
