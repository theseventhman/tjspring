package com.tj.exercise.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tj
 * @Date: 2022/3/14 21:55
 */
public class UserDao {
    private static Map<String,String> hashMap = new HashMap<String, String>();

    static {
        hashMap.put("10001","test1");
        hashMap.put("10002","test2");
        hashMap.put("10003","test3");
    }

    public String queryUserName(String uId){
        return hashMap.get(uId);
    }
}
