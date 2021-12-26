package com.algorithm.demo.entity;

import java.io.Serializable;

/**
 * 算法演示系统用户表(User)实体类
 *
 * @author makejava
 * @since 2021-12-22 13:29:48
 */
public class User implements Serializable {
    private static final long serialVersionUID = 236567286624691246L;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsSupper() {
        return isSupper;
    }

    public void setIsSupper(Integer isSupper) {
        this.isSupper = isSupper;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}

