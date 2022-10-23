package com.tj.exercise.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.BeanFactory;
import com.tj.exercise.springframework.beans.factory.FactoryBean;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.tj.exercise.springframework.core.util.ClassUtils;
import com.tj.exercise.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:35
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for(StringValueResolver resolver : this.embeddedValueResolvers){
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    @Override
    public Object getBean(String name) throws BeansException{
       return doGetBean(name,null);
   }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return (T)getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if(sharedInstance !=null){
            return  (T) getObjectForBeanInstance(sharedInstance,name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name,beanDefinition,args);
        return (T) getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if(object == null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }

        return object;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws  BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws  BeansException;

    /**
     * Return the list of BeanPostProcessors that will get applied
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return  this.beanPostProcessors;
    }

    public  ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
