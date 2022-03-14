package com.tj.exercise.springframework.beans.factory.config;

/**
 * @Author: tj
 * @Date: 2022/3/14 21:48
 */
public class BeanReference {
    public String getBeanName() {
        return beanName;
    }

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    private final String beanName;
}
