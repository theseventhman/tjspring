package com.tj.exercise.springframework.context;

import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.Aware;

/**
 * @Author: tj
 * @Date: 2022/3/17 17:59
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
