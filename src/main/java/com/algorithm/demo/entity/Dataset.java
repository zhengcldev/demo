package com.algorithm.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据集表，数据集信息描述(Dataset)实体类
 *
 * @author makejava
 * @since 2022-04-03 17:04:01
 */
@Data
public class Dataset implements Serializable {
    private static final long serialVersionUID = -24869742058837895L;
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
    /**
     * 是否是稀疏数据集
     */
    private Integer isSparse;
    /**
     * 数据集信息描述
     */
    private String description;
    /**
     * 数据集交易条数
     */
    private Integer transCount;
    /**
     * 数据集不同项个数
     */
    private Integer itemCount;
    /**
     * 数据集平均交易长度
     */
    private Float avgTransLen;
    /**
     * 最大交易长度
     */
    private Integer maxTransLen;

    private String dataUrl;


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

    public Integer getIsSparse() {
        return isSparse;
    }

    public void setIsSparse(Integer isSparse) {
        this.isSparse = isSparse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTransCount() {
        return transCount;
    }

    public void setTransCount(Integer transCount) {
        this.transCount = transCount;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Float getAvgTransLen() {
        return avgTransLen;
    }

    public void setAvgTransLen(Float avgTransLen) {
        this.avgTransLen = avgTransLen;
    }

    public Integer getMaxTransLen() {
        return maxTransLen;
    }

    public void setMaxTransLen(Integer maxTransLen) {
        this.maxTransLen = maxTransLen;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

}

