package com.tj.exercise.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/16 18:24
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext(){

    }

    /**
     * 从 XML 中加载 BeanDefinition, 并刷新上下文
     * @param configLocations
     * @throws BeanException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeanException {
       this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition, 并刷新上下文
     * @param configLocations
     * @throws BeanException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeanException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }


}
