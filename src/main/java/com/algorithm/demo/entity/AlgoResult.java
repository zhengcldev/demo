package com.algorithm.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/25 上午11:12
 */
@Data
public class AlgoResult implements Serializable {

    private static final long serialVersionUID = 514145933600519875L;
    /**
     * 算法名称
     */
    private String algoName;
    /**
     * 数据集名称
     */
    private String dataName;
    /**
     * 运行时间
     */
    private String runTime;
    /**
     * 运行空间
     */
    private String runMemory;
    /**
     * 搜索空间
     */
    private String searchSpace;
    /**
     * 潜在SFUI
     */
    private String PSFUI;
    /**
     * 实际SFUI
     */
    private String SFUI;

    public AlgoResult() {
    }
}
