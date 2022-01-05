package com.algorithm.demo.enumeration;

/**
 * @描述: 返回状态枚举类
 * @author: zhengchuanlong
 * @date: 2021/12/14 下午1:55
 */
public enum StatusEnum {
    LOGIN_SUCCESS("200", "登陆成功"),
    LOGIN_ERROR("401", "登陆失败"),
    ALGO_RUN_SUCCESS("202","算法运行成功"),
    ALGO_RUN_FAIL("402","算法运行失败"),
    OPERATION_SUCCESS("203","操作成功"),
    OPERATION_FAIL("403","操作失败");

    /**
     * 返回状态码
     */
    private final String statusCode;
    /**
     * 返回状态信息
     */
    private final String statusMsg;

    StatusEnum(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
    //获取状态码
    public String getStatusCode(){
        return statusCode;
    }

    //获取提示信息
    public String getStatusMsg(){
        return statusMsg;
    }
}
