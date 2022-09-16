package com.tj.exercise.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @Author: tj
 * @Date: 2022/5/22 22:06
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
