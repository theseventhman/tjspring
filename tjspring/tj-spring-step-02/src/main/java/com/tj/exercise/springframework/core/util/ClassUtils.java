package com.tj.exercise.springframework.core.util;

import com.tj.exercise.springframework.context.ApplicationListener;

/**
 * @Author: tj
 * @Date: 2022/3/15 10:02
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c1 = null;
        try{
            c1 = Thread.currentThread().getContextClassLoader();
        }
        catch(Throwable ex){

        }
        if(c1 == null){
            c1 = ClassUtils.class.getClassLoader();
        }
        return c1;
    }

    public static boolean isCglibProxyClass(Class<? > claszz) {
        return (claszz != null && isCglibProxyClassName(claszz.getName()));
    }

    /**
     * Check whether the specified class name is a GCLIB-generated class.
     * @param className the class name to check
     * @return
     */
    private static boolean isCglibProxyClassName(String className) {
        return (className !=null && className.contains("$$"));
    }
}
