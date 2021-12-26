package com.algorithm.demo.entity;

import java.io.Serializable;

/**
 * 数据集表，数据集信息描述(Dataset)实体类
 *
 * @author makejava
 * @since 2021-12-25 21:53:52
 */
public class Dataset implements Serializable {
    private static final long serialVersionUID = -32782395211914417L;
    /**
     * 数据集表自增id
     */
    private Integer id;
    /**
     * 算法名称
     */
    private String dataName;
    /**
     * 是否是真实数据集，默认不是
     */
    private Integer isReal;
    /**
     * 添加者姓名
     */
    private String addName;
    /**
     * 添加时间
     */
    private String addTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getIsReal() {
        return isReal;
    }

    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

}

