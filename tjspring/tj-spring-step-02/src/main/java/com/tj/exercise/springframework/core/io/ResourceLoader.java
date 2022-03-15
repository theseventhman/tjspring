package com.tj.exercise.springframework.core.io;

/**
 * @Author: tj
 * @Date: 2022/3/15 10:12
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath";

    Resource getResource(String location);
}
