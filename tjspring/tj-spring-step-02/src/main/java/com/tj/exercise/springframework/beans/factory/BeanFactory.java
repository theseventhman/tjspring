package com.tj.exercise.springframework.beans.factory;

import com.tj.exercise.springframework.beans.BeansException;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:32
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}
