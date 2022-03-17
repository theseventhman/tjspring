package com.tj.exercise.springframework.beans.factory;

/**
 * @Author: tj
 * @Date: 2022/3/17 17:56
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
