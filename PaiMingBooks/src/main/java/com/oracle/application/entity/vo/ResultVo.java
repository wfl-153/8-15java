package com.oracle.application.entity.vo;

import java.io.Serializable;

/**
 * @Author 王飞龙
 * @Date 2023/6/27 9:37
 * @Version 1.0
 */
public class ResultVo implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public ResultVo() {
    }


    public static ResultVo createSuccess(String msg,Object data){
        return new ResultVo(200,msg,data);
    }

    public static ResultVo createError(String msg){
        return new ResultVo(500,msg);
    }

    public static ResultVo NotLogin(String msg){
        return new ResultVo(300,msg);
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
