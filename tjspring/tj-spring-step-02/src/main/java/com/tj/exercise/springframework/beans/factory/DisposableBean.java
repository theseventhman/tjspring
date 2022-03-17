package com.tj.exercise.springframework.beans.factory;

/**
 * @Author: tj
 * @Date: 2022/3/17 10:44
 */
public interface DisposableBean {
    void destroy() throws  Exception;
}
