package com.tj.exercise.springframework.context.event;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.BeanFactory;
import com.tj.exercise.springframework.beans.factory.BeanFactoryAware;
import com.tj.exercise.springframework.context.ApplicationEvent;
import com.tj.exercise.springframework.context.ApplicationListener;
import com.tj.exercise.springframework.core.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @Author: tj
 * @Date: 2022/3/18 10:51
 */
public abstract class AbstractApplicationEventMulticaster implements  ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
       applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
       applicationListeners.remove(listener);
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for(ApplicationListener<ApplicationEvent> listener : applicationListeners){
            if(supportsEvent(listener,event)) allListeners.add(listener);
        }
        return allListeners;
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument =  ((ParameterizedType)genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try{
            eventClassName = Class.forName(className);
        } catch(ClassNotFoundException e){
            throw new BeansException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
