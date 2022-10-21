package com.tj.exercise.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Author: tj
 * @Date: 2022/10/21 23:51
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";
}
