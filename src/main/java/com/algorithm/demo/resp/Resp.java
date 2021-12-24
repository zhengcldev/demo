package com.algorithm.demo.resp;

import com.algorithm.demo.enumeration.StatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Resp<T> implements Serializable {

    /**
     * 返回状态码
     */
    private final String status;

    /**
     * 返回消息
     */
    private final String message;

    /**
     * 返回内容
     */
    private T data;

    public Resp(){
        this.status = StatusEnum.LOGIN_SUCCESS.getStatusCode();
        this.message = StatusEnum.LOGIN_SUCCESS.getStatusMsg();
    }

    public Resp(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Resp(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
