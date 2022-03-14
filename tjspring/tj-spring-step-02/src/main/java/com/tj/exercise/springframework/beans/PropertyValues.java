package com.tj.exercise.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tj
 * @Date: 2022/3/14 21:36
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getProvertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for(PropertyValue propertyValue: propertyValueList){
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }
}
