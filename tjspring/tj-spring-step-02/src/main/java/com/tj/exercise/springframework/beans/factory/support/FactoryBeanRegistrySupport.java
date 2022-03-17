package com.tj.exercise.springframework.beans.factory.support;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.FactoryBean;
import sun.util.resources.th.CalendarData_th;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: tj
 * @Date: 2022/3/17 21:16
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();


    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName){
        if(factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object == null){
                object = doGetObjectFromFactoryBean(factory,beanName);
                this.factoryBeanObjectCache.put(beanName,(object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else{
            return doGetObjectFromFactoryBean(factory,beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, String beanName) {
        try{
            return factory.getObject();
        } catch (Exception e){
            throw new BeansException("FactoryBean threw exception on object[" + beanName +"] creation",e);
        }
    }
}
