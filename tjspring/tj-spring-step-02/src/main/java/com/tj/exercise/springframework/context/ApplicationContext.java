package com.tj.exercise.springframework.context;

import com.tj.exercise.springframework.beans.factory.HierarchicalBeanFactory;
import com.tj.exercise.springframework.beans.factory.ListableBeanFactory;
import com.tj.exercise.springframework.core.io.ResourceLoader;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:59
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
