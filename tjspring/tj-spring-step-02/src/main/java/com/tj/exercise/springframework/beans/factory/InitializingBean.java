package com.tj.exercise.springframework.beans.factory;

/**
 * @Author: tj
 * @Date: 2022/3/17 10:43
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
