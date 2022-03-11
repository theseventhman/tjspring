package com.tj.exercise.springframework.beans.factory.support;

import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:42
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
       Object bean;

        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed",e);
        }

        addSingleton(beanName,bean);
        return bean;

    }


}
