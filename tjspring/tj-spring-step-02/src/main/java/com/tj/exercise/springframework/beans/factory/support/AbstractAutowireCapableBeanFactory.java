package com.tj.exercise.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.PropertyValue;
import com.tj.exercise.springframework.beans.PropertyValues;
import com.tj.exercise.springframework.beans.factory.*;
import com.tj.exercise.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.beans.factory.config.BeanReference;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import jdk.nashorn.internal.runtime.ECMAException;

import java.awt.image.BandCombineOp;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:42
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
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
            bean = initializeBean(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        if(beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return bean;

    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
     if(!beanDefinition.isSingleton()) return;

      if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestoryMethodName())){
          registerDisposableBean(beanName, new DisposableBeanAdapter(bean,beanName,beanDefinition));
      }
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

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware)bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware)bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware)bean).setBeanName(beanName);
            }
        }
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch(Exception e){
            throw new BeansException("Invocation of init method of bean[" + beanName +"] failed",e);
        }

        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean,beanName);
        return wrappedBean;
    }



    @Override
    public  Object applyBeanPostProcessorsAfterInitialization(Object existBean, String beanName) {
       Object result = existBean;
       for(BeanPostProcessor processor : getBeanPostProcessors()){
           Object current = processor.postProcessAfterInitialization(result,beanName);
           if(null == current) return result;
           result = current;
       }
       return result;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws  Exception {
       if(bean instanceof InitializingBean){
           ((InitializingBean) bean).afterPropertiesSet();
       }

       String initMethodName = beanDefinition.getInitMethodName();
       if(StrUtil.isNotEmpty(initMethodName) && !(bean instanceof  InitializingBean)){
           Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
           if(null == initMethod){
               throw new BeansException("Could not find an init method named '" + initMethodName +"' on bean with name '"  +beanName +"'");
           }
           initMethod.invoke(bean);
       }

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for(BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }


}
