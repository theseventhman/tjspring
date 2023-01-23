package com.tj.exercise.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

/**
 * @Author: tj
 * @Date: 2023/1/19 12:56
 */
public interface ObjectFactory<T> {
    T getObject() throws BeanException;
}
