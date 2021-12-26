package com.algorithm.demo.entity;

import java.io.Serializable;

/**
 * 轮廓模式挖掘算法表(Algorithm)实体类
 *
 * @author makejava
 * @since 2021-12-26 13:19:10
 */
public class Algorithm implements Serializable {
    private static final long serialVersionUID = 949611231076856966L;
    /**
     * 算法表自增id
     */
    private Integer id;
    /**
     * 算法名字
     */
    private String algoName;
    /**
     * 提出算法作者
     */
    private String algoAuthor;
    /**
     * 算法提出年份
     */
    private Integer algoYear;
    /**
     * 添加算法的用户名称
     */
    private String addName;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 是否是提出算法，0表示对比算法，1表示原算法
     */
    private Integer isSource;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getAlgoAuthor() {
        return algoAuthor;
    }

    public void setAlgoAuthor(String algoAuthor) {
        this.algoAuthor = algoAuthor;
    }

    public Integer getAlgoYear() {
        return algoYear;
    }

    public void setAlgoYear(Integer algoYear) {
        this.algoYear = algoYear;
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

    public Integer getIsSource() {
        return isSource;
    }

    public void setIsSource(Integer isSource) {
        this.isSource = isSource;
    }

}

