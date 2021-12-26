package com.algorithm.demo.entity;

import java.io.Serializable;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/25 上午11:12
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getRunMemory() {
        return runMemory;
    }

    public void setRunMemory(String runMemory) {
        this.runMemory = runMemory;
    }

    public String getSearchSpace() {
        return searchSpace;
    }

    public void setSearchSpace(String searchSpace) {
        this.searchSpace = searchSpace;
    }

    public String getPSFUI() {
        return PSFUI;
    }

    public void setPSFUI(String PSFUI) {
        this.PSFUI = PSFUI;
    }

    public String getSFUI() {
        return SFUI;
    }

    public void setSFUI(String SFUI) {
        this.SFUI = SFUI;
    }
}
