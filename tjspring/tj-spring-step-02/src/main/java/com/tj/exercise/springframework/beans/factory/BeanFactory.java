package com.tj.exercise.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.BeansException;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:32
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeanException;
}
