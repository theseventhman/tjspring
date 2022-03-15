package com.tj.exercise.springframework.context;

import cn.hutool.core.bean.BeanException;

/**
 * @Author: tj
 * @Date: 2022/3/15 21:59
 */
public interface ConfigurableApplicationContext extends  ApplicationContext {

    /**
     * 刷新容器
     * @throws BeanException
     */
    void refresh() throws BeanException;
}
