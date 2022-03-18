package com.tj.exercise.springframework.beans.factory.support;

import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.DisposableBean;
import com.tj.exercise.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:19
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    private final Map<String,Object> singletonObjects = new HashMap<String, Object>();

    private final Map<String,DisposableBean> disposableBeans = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }

    protected void addSingleton(String beanName, Object singleObject){
        singletonObjects.put(beanName, singleObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
       disposableBeans.put(beanName,bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for(int i = disposableBeanNames.length - 1; i >= 0; i--){
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try{
                disposableBean.destroy();
            } catch (Exception e){
                throw  new BeansException("Destroy method on bean with name '" +beanName +"' threw an exception",e);
            }
        }
    }


}
