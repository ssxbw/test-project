package com.payment.common;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class HotelBaseContext {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Integer id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Integer getCurrentId(){
        return threadLocal.get();
    }
}
