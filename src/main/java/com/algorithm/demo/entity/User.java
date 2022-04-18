package com.algorithm.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 算法演示系统用户表(User)实体类
 *
 * @author makejava
 * @since 2022-03-22 02:32:06
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -35129574189872626L;
    /**
     * 用户表自增id
     */
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别，默认为男
     */
    private String sex;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 是否是超级管理员，默认否
     */
    private Integer isSupper;
    /**
     * 创建者名字
     */
    private String creator;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新者
     */
    private String updater;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 住址
     */
    private String address;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 所属机构
     */
    private String dept;
    /**
     * 个人简介
     */
    private String introduction;
    /**
     * 头像地址
     */
    private String pfpUrl;
    /**
     * 账号状态
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

