package com.tj.exercise.springframework.context.event;

import com.tj.exercise.springframework.beans.factory.BeanFactory;
import com.tj.exercise.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.tj.exercise.springframework.context.ApplicationEvent;
import com.tj.exercise.springframework.context.ApplicationListener;

import java.util.zip.ZipEntry;

/**
 * @Author: tj
 * @Date: 2022/3/18 11:38
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for(final ApplicationListener listener : getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }
}
