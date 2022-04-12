package com.algorithm.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统日志表(Log)实体类
 *
 * @author makejava
 * @since 2022-03-30 15:50:45
 */
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = -65883235353097452L;
    /**
     * 自增id
     */
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 退出时间
     */
    private String exitTime;
    /**
     * 修改密码功能
     */
    private Integer updatePwd;
    /**
     * 编辑用户功能
     */
    private Integer editUser;
    /**
     * 阅读论文功能
     */
    private Integer readPaper;
    /**
     * 编辑数据集功能
     */
    private Integer editData;
    /**
     * 实时运行功能
     */
    private Integer algoRuntime;
    /**
     * 离线运行功能
     */
    private Integer algoOffline;
    /**
     * 个人信息功能
     */
    private Integer userInfo;
    /**
     * 登录失败次数
     */
    private Integer loginFail;


}

