package com.tj.exercise.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.tj.exercise.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.tj.exercise.springframework.context.ApplicationEvent;
import com.tj.exercise.springframework.context.ApplicationListener;
import com.tj.exercise.springframework.context.ConfigurableApplicationContext;
import com.tj.exercise.springframework.context.event.ApplicationEventMulticaster;
import com.tj.exercise.springframework.context.event.ContextClosedEvent;
import com.tj.exercise.springframework.context.event.ContextRefreshedEvent;
import com.tj.exercise.springframework.context.event.SimpleApplicationEventMulticaster;
import com.tj.exercise.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/16 9:21
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME  = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void refresh() throws BeanException {
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        invokeBeanFactoryPostProcessors(beanFactory);

        registerBeanPostProcessors(beanFactory);

        initApplicationEventMulticaster();

        registerListeners();

        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        finishRefresh();
    }

    /**
     * 发布容器刷新完成事件
     */
    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * 注册事件监听器
     */
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for(ApplicationListener listener : applicationListeners){
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 初始化事件发布者
     */
    private void initApplicationEventMulticaster() {
        ConfigurableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }


    /**
     * 创建BeanFactory, 并加载BeanDefinition
     * @throws BeanException
     */
    protected abstract void refreshBeanFactory() throws BeanException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 在Bean 实例化之前, 执行 BeanFactoryPostProcessor
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }



    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(name,requiredType);
    }

    @Override
    public void registerShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {

       publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }
}
