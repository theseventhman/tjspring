package com.tj.exercise.springframework.test.bean;

/**
 * @Author: tj
 * @Date: 2023/1/22 21:54
 */
public class Husband {
    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }
    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
