package com.tj.exercise.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.tj.exercise.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: tj
 * @Date: 2022/3/16 9:48
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeanException {
       DefaultListableBeanFactory beanFactory = createBeanFactory();
       loadBeanDefinitions(beanFactory);
       this.beanFactory = beanFactory;
    }



    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
