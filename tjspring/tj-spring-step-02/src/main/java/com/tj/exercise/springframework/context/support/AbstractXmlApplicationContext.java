package com.tj.exercise.springframework.context.support;

import com.tj.exercise.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.tj.exercise.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: tj
 * @Date: 2022/3/16 18:17
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocation = getConfigLocations();
        if(null != configLocation){
            beanDefinitionReader.loadBeanDefinitions(configLocation);
        }
    }

    protected abstract String[] getConfigLocations();
}
