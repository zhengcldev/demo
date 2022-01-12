package com.algorithm.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/27 下午10:21
 */
@Configuration
// prefix代表属性文件中的前缀
@ConfigurationProperties(prefix = "constant")
// @PropertySource指定加载的那个属性文件，如是默认的application.properties 则不用指定
@PropertySource("classpath:constant.properties")
@Data
public class Constant {
    private String[] sourceName;
    private String[] targetName;
    private String[] dataName;
    public static final String baseUrl = "http://127.0.0.1:8864";
    public static final String Algo_SFU_Miner = "SFU-Miner";
    public static final String Algo_SFUI_UF = "SFUI-UF";
    public static final String Algo_SKYFUP_D = "SKYFUP-D";
    public static final String Algo_SKYFUP_B = "SKYFUP-B";

}
