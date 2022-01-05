package com.algorithm.demo.entity;

import lombok.Data;

/**
 * @描述: 验证码类
 * @author: zhengchuanlong
 * @date: 2022/1/5 下午6:50
 */

@Data
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;

}


