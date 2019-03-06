package com.ck.multimoduledao.entity;

public class ResultData {
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回code代码
     */
    private Integer code = 200;
    /**
     * 返回内容
     */
    private Object data;
    /**
     * 返回状态
     */
    private boolean success = true;

    public ResultData(){

    }

    public static ResultData instance(){
        return new ResultData();
    }

    public ResultData(String msg){
        this.msg = msg;
    }

    public ResultData(Object obj){
        this.data = obj;
    }

    public static ResultData ok() {
        return instance().setSuccess(true);
    }

    public static ResultData ok(Object data) {
        return ok().setData(data);
    }

    public static ResultData ok(Object data, String msg) {
        return ok(data).setMsg(msg);
    }

    public static ResultData error() {
        return instance().setSuccess(false);
    }

    public static ResultData error(String msg) {
        return error().setSuccess(false);
    }

    public String getMsg() {
        return msg;
    }

    public ResultData setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResultData setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultData setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultData setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
