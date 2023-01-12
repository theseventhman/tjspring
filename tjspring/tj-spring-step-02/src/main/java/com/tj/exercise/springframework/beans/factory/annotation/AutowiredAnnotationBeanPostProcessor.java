package com.tj.exercise.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.PropertyValues;
import com.tj.exercise.springframework.beans.factory.BeanFactory;
import com.tj.exercise.springframework.beans.factory.BeanFactoryAware;
import com.tj.exercise.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.tj.exercise.springframework.core.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * @Author: tj
 * @Date: 2022/10/21 23:55
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory)beanFactory;
    }
    @Override
    public PropertyValues postProcessorPropertyValues(PropertyValues pvs, Object bean, String beanName){
       // 1.处理注解 @Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        for(Field field : declaredFields){
            Value valueAnnotation = field.getAnnotation(Value.class);
            if(null != valueAnnotation){
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean,field.getName(),value);
            }
        }

        // 2. 处理注解 @AutoWired
        for(Field field : declaredFields){
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if(null != autowiredAnnotation){
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnoatation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if(null != qualifierAnnoatation){
                    dependentBeanName = qualifierAnnoatation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName,fieldType);
                } else{
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean,field.getName(),dependentBean);
            }
        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }
}
