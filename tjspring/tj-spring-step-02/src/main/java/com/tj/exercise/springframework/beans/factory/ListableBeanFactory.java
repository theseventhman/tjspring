package com.tj.exercise.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.factory.config.BeanFactoryPostProcessor;

import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:47
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeanException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     *
     * @return the name of all beans defined in this registry
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
