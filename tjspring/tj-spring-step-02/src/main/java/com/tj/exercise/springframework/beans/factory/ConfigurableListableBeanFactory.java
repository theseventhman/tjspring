package com.tj.exercise.springframework.beans.factory;

import com.tj.exercise.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:43
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
}
