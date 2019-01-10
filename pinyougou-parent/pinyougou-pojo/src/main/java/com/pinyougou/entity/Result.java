package com.pinyougou.entity;

import java.io.Serializable;

/**
 * 返回封装结果
 */
public class Result implements Serializable {

    private boolean success;  //是否成功
    private String message;   //错误提示

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
