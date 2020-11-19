package cn.qinwh.qbookcommon.utils;

import java.io.Serializable;

/**
 * @program: qbook
 * @description: 返回数据
 * @author: qinwh
 * @create: 2020-11-19 13:03
 **/
public class ReturnMsg<T> implements Serializable {

    /**
     * 返回编码
     */
    private String code = SUCCESS;

    /**
     * 返回提示消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 无参构造器
     */
    public ReturnMsg(){
        msg = "";
        data = null;
    }
    /**
     * 有参构造器
     * @param code 返回编码
     * @param msg 返回信息
     * @param data 返回数据
     * @return ReturnMsg
     */
    public ReturnMsg(String code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 成功
     */
    public static final String SUCCESS="0";
    /**
     * 失败
     */
    public static final String FAIL="-1";
    /**
     * 警告
     */
    public static final String WAINING="-2";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
