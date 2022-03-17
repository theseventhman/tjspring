package com.tj.exercise.springframework.beans.factory;

/**
 * @Author: tj
 * @Date: 2022/3/17 13:25
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
