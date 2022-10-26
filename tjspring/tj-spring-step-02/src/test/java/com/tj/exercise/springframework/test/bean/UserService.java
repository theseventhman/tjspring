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

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001")+", " + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
