package com.tj.exercise.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:54
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前, 执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;

    /**
     * 在 Bean 对象执行初始化方法之后, 执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws  BeanException;

}
