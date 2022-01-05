package com.algorithm.demo.service;

import com.algorithm.demo.entity.VerifyCode;

import java.io.IOException;
import java.io.OutputStream;
/**
 * @描述: 生成验证码并返回code，将图片写的os中
 * @author: zhengchuanlong
 * @date: 2022/1/5 下午6:53
 */

public interface VerifyCodeService {

    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     *
     * @param width 图片宽
     * @param height 图片长
     * @return 验证码对象
     * @throws IOException 抛出io异常
     */
    VerifyCode generate(int width, int height) throws IOException;
}


