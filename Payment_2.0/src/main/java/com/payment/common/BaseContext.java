package com.payment.common;

import org.springframework.stereotype.Component;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
@Component
public class BaseContext {
    //private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static int id = -1;

    private static String type = "";


    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Integer id){
        BaseContext.id = id;
    }
    public static void setCurrentType(String type){
        BaseContext.type = type;
    }

    /**
     * 获取值
     * @return
     */
    public static Integer getCurrentId(){
        return BaseContext.id;
    }
    public static String getCurrentType(){
        return BaseContext.type;
    }
}