package com.tj.exercise.springframework.test.bean;

import com.sun.org.apache.bcel.internal.generic.ISUB;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import com.tj.exercise.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/17 21:37
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy,method,args) ->{
            if("toString".equals(method.getName())) return this.toString();

            Map<String,String> hashMap = new HashMap<>();
            hashMap.put("10001","test1");
            hashMap.put("10002","test2");
            hashMap.put("10003","test3");

            return "你被代理了 " + method.getName() +": " + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[] {IUserDao.class},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
