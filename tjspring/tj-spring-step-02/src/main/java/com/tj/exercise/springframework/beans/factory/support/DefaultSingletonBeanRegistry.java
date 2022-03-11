package com.tj.exercise.springframework.beans.factory.support;

import com.tj.exercise.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:19
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String,Object> singletonObjects = new HashMap<String, Object>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singleObject){
        singletonObjects.put(beanName, singleObject);
    }
}
