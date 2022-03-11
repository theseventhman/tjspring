package com.tj.exercise.springframework.beans.factory.support;

import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.BeanFactory;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:35
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
   @Override
    public Object getBean(String name) throws BeansException{
        Object bean = getSingleton(name);
        if(bean !=null){
            return  bean;
        }

       BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws  BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws  BeansException;
}
