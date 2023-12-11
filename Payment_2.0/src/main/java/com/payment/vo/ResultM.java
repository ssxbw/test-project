package com.payment.vo;

import lombok.Data;

@Data
public class ResultM<T> {
    public int code;
    public String msg;
    public T data;

    public static <T> ResultM success(){
        ResultM r = new ResultM();
        r.code=1;
        r.msg="success";
        r.data=null;
        return r;
    }
    public static <T> ResultM fail(String msg, T data){
        ResultM r= new ResultM();
        r.code=2;
        r.msg=msg;
        r.data=data;
        return r;
    }
    public static <T> ResultM success(String msg){
        ResultM r = new ResultM(1,msg,null);
        return r;
    }
    public static <T> ResultM success(String msg, T data){
        ResultM r = new ResultM(1,msg,data);
        return r;
    }
//    public static <T> Result false(String msg, T data){
//        Result r =new Result(0,msg,data);
//        return r;
//    }
    public static <T> ResultM success(T data){
        ResultM r = new ResultM(1,"success",data);
        return r;
    }
    private ResultM(int code, String msg, T data){
        this.msg=msg;
        this.data=data;
        this.code=code;
    }
    public ResultM(){

    }
}
