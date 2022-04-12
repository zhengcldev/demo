package com.algorithm.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2022/3/26 下午8:33
 */
@Data
public class Visit implements Serializable {
    private static final long serialVersionUID = -8066237106516921195L;
    /**
     * 访问时间
     */
    String date;
    /**
     * 每天访问次数
     */
    int times;
}
