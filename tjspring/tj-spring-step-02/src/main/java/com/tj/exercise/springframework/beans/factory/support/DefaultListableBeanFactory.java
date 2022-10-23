package com.tj.exercise.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/11 11:19
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition ==null) throw new BeansException("No bean named '" + beanName +"' is defined");
        return beanDefinition;
    }



    @Override
    public void preInstantiateSingletons() throws BeanException {
      beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        Map<String,T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition) ->{
            Class beanClass = beanDefinition.getBeanClass();
            if(type.isAssignableFrom(beanClass)){
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }



    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for(Map.Entry<String,BeanDefinition> entry : beanDefinitionMap.entrySet()){
            Class beanClass = entry.getValue().getBeanClass();
            if(requiredType.isAssignableFrom(beanClass)){
                beanNames.add(entry.getKey());
            }
        }
        if( 1 == beanNames.size()){
            return getBean(beanNames.get(0),requiredType);
        }
        throw new BeansException(requiredType + "expected single bean bout found " + beanNames.size() +": " + beanNames);
    }


}
