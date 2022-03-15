package com.tj.exercise.springframework.test.bean;

/**
 * @Author: tj
 * @Date: 2022/3/11 11:30
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String queryUserInfo(){
        return userDao.queryUserName(uId);
    }
}
