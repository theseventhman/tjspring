package com.tj.exercise.springframework.test.bean;

import com.tj.exercise.springframework.beans.BeansException;
import com.tj.exercise.springframework.beans.factory.*;
import com.tj.exercise.springframework.beans.factory.annotation.Autowired;
import com.tj.exercise.springframework.beans.factory.annotation.Value;
import com.tj.exercise.springframework.context.ApplicationContext;
import com.tj.exercise.springframework.context.ApplicationContextAware;
import com.tj.exercise.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: tj
 * @Date: 2022/3/11 11:30
 */
@Component("userService")
public class UserService implements IUserService {


    private String token;



    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test，100001，深圳，" + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
