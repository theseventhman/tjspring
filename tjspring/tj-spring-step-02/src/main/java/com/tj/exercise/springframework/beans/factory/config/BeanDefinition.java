package com.tj.exercise.springframework.beans.factory.config;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:15
 */
public class BeanDefinition {
    private Class beanClass;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }
}
