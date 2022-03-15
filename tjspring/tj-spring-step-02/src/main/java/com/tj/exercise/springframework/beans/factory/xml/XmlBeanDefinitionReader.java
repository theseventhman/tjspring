package com.tj.exercise.springframework.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.PropertyValue;
import com.tj.exercise.springframework.beans.factory.config.BeanDefinition;
import com.tj.exercise.springframework.beans.factory.config.BeanReference;
import com.tj.exercise.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.tj.exercise.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.tj.exercise.springframework.core.io.Resource;
import com.tj.exercise.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: tj
 * @Date: 2022/3/15 10:47
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try{
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e){
            throw  new BeanException("IOException parsing XML document from " + resource,e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
       for(Resource resource : resources){
           loadBeanDefinitions(resource);
       }
    }

    @Override
    public void loadBeanDefinitions(String loation) throws BeanException {
       ResourceLoader resourceLoader = getResourceLoader();
       Resource resource = resourceLoader.getResource(loation);
       loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException{
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for(int i = 0; i < childNodes.getLength(); i++){
            if(!(childNodes.item(i) instanceof  Element)){
                continue;
            }
            if(!"bean".equals(childNodes.item(i).getNodeName())) {continue;}

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for(int j = 0; j < bean.getChildNodes().getLength(); j++){
                if(!(bean.getChildNodes().item(j) instanceof  Element)) continue;;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;;
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw  new BeansException("Duplicate beanName[" +beanName +"] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }
}
