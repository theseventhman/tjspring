package com.tj.exercise.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @Author: tj
 * @Date: 2022/5/22 22:08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
