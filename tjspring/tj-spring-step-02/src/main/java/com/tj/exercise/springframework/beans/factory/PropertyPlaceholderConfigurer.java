package com.tj.exercise.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.PropertyValue;
import com.tj.exercise.springframework.beans.PropertyValues;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.tj.exercise.springframework.core.io.DefaultResourceLoader;
import com.tj.exercise.springframework.core.io.Resource;

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
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for(String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for(PropertyValue propertyValue : propertyValues.getProvertyValues()) {
                    Object value = propertyValue.getValue();
                    if(!(value instanceof  String)) continue;
                    String strVal = (String)value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHODER_PREFIX);
                    int stopIds = strVal.indexOf(DEFAULT_PLACEHODLER_SUFFIX);
                    if(startIdx !=-1 && stopIds !=-1 && startIdx <stopIds) {
                        String propKey = strVal.substring(startIdx + 2, stopIds);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx,stopIds +1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),buffer.toString()));
                    }
                }
            }
        } catch (IOException e){
            throw new BeansException("Could not load properties",e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
