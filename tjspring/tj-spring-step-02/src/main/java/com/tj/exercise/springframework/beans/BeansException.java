package com.tj.exercise.springframework.beans;

/**
 * @Author: tj
 * @Date: 2022/3/11 10:33
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg) {super(msg);}

    public BeansException(String msg, Throwable cause) {super(msg,cause);}
}
