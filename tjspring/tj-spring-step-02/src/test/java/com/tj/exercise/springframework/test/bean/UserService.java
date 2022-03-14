package com.tj.exercise.springframework.test.bean;

/**
 * @Author: tj
 * @Date: 2022/3/11 11:30
 */
public class UserService {
    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }

    private String name;

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息:" + name);

    }
}
