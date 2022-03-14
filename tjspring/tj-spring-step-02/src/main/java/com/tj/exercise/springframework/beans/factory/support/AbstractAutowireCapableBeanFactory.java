package com.tj.exercise.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.PropertyValue;
import com.tj.exercise.springframework.beans.PropertyValues;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanReference;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import jdk.nashorn.internal.runtime.ECMAException;

import java.lang.reflect.Constructor;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:42
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
       Object bean = null;

        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
            applyPropertyValues(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }

        addSingleton(beanName,bean);
        return bean;

    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue : propertyValues.getProvertyValues()){

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e){
            throw  new BeansException("Error setting property values: " + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors){
            if(null !=args && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }




}
