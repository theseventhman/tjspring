package com.tj.exercise.springframework.test.common;

import cn.hutool.core.bean.BeanException;
import com.tj.exercise.springframework.beans.factory.config.BeanPostProcessor;
import com.tj.exercise.springframework.test.bean.UserService;

/**
 * @Author: tj
 * @Date: 2022/3/16 21:25
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("改为: justForTest");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
