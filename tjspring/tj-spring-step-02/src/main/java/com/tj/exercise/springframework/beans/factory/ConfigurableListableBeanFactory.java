package com.tj.exercise.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:43
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws  BeanException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

   void preInstantiateSingletons() throws BeanException;
}
