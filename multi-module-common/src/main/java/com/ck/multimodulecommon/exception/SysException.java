package com.ck.multimodulecommon.exception;

/**
 * @author ck
 * @date 2018/11/2 17:03
 * Description  : 自定义异常
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 异常描述
     */
    private String msg;
    /**
     * 异常代码
     */
    private int code = 500;
    public SysException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public SysException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public SysException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public SysException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
