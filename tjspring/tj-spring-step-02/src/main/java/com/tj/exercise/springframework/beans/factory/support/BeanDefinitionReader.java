package com.tj.exercise.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.core.io.Resource;
import com.tj.exercise.springframework.core.io.ResourceLoader;
import javafx.scene.media.VideoTrack;

/**
 * @Author: tj
 * @Date: 2022/3/15 10:22
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(Resource... resources) throws BeanException;

    void loadBeanDefinitions(String loation) throws  BeanException;

}
