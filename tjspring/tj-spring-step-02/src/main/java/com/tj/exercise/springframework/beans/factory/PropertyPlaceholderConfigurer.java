package com.tj.exercise.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.PropertyValue;
import com.tj.exercise.springframework.beans.PropertyValues;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.tj.exercise.springframework.core.io.DefaultResourceLoader;
import com.tj.exercise.springframework.core.io.Resource;
import com.tj.exercise.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: tj
 * @Date: 2022/5/22 21:50
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHODER_PREFIX ="${";

    /**
     * Default placehodler suffix: {@value}
     */
    public static final String DEFAULT_PLACEHODLER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        try{
            // 加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            // 占位符替换属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for(String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for(PropertyValue propertyValue : propertyValues.getProvertyValues()) {
                    Object value = propertyValue.getValue();
                    if(!(value instanceof  String)) continue;
                       value = resolvePlaceholder((String) value, properties);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),value));

                }

                // 向容器中添加字符串解析器, 供解析@Value注解使用
                StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
                beanFactory.addEmbeddedValueResolver(valueResolver);
            }
        } catch (IOException e){
            throw new BeansException("Could not load properties",e);
        }
    }

    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHODER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHODLER_SUFFIX);
        if (stopIdx !=-1 && stopIdx !=-1 && startIdx < stopIdx){
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx,stopIdx + 1, propVal);
        }
        return  buffer.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal,properties);
        }
    }
}
