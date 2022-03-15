package com.tj.exercise.springframework.core.util;

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
}
