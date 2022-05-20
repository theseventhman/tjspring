package com.tj.exercise.springframework.test.bean;

import java.util.Random;

/**
 * @Author: tj
 * @Date: 2022/5/13 22:02
 */
public class ThirteenChapterUserService implements IUserService {
    @Override
    public String queryUserInfo() {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return "小傅哥,10001,深圳";
    }

    @Override
    public String register(String userName) {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return "注册用户: " + userName +"success! ";
    }
}
