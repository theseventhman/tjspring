package com.tj.exercise.springframework.beans;

/**
 * @Author: tj
 * @Date: 2022/3/14 21:32
 */
public class PropertyValue {
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    private final String name;

    private final Object value;
}
