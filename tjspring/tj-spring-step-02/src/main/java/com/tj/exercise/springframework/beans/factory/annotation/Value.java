package com.tj.exercise.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Author: tj
 * @Date: 2022/10/21 23:53
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
