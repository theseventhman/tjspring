package com.tj.exercise.springframework.aop;

/**
 * @Author: tj
 * @Date: 2022/3/22 21:44
 */
public interface ClassFilter {

    /**
     *  Should the pointcut apply to the given interface or target class?
      * @param clazz the candidate target class
     * @return whether the adivce should apply to the given target class
     */
  boolean matches(Class<?> clazz);


}
