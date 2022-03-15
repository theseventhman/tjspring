package com.tj.exercise.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:41
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后, 实例化 Bean对象之前, 提供修改 BeanDefnition 属性的机制
     * @param beanFactory
     * @throws BeanException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
