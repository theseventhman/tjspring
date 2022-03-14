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
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public Object getBean(String name) throws BeansException{
       return doGetBean(name,null);
   }

    protected <T> T doGetBean(final String name,final Object[] args) {
        Object bean = getSingleton(name);
        if(bean !=null){
            return  (T)bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name,beanDefinition,args);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws  BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws  BeansException;
}
