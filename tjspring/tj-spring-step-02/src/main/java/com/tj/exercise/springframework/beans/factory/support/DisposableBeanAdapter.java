package com.tj.exercise.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.DisposableBean;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: tj
 * @Date: 2022/3/17 10:59
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestoryMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof  DisposableBean){
            ((DisposableBean) bean).destroy();
        }

        if(StrUtil.isNotEmpty(destroyMethodName) && !( bean instanceof  DisposableBean && "destory".equals(this.destroyMethodName))) {
            Method destoryMethod = bean.getClass().getMethod(destroyMethodName);
            if(null == destoryMethod) {
                throw new BeansException("Couldn't find a destory method named '" + destroyMethodName +"' on bean with name '" + beanName +"'");
            }
            destoryMethod.invoke(bean);
        }
    }
}
