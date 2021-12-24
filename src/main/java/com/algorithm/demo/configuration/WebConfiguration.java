//package com.algorithm.demo.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @描述:
// * @author: zhengchuanlong
// * @date: 2021/12/21 下午8:32
// */
//@Configuration
//public class WebConfiguration {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("GET", "POST")
//                        .allowedHeaders("header1","header2")
//                        .exposedHeaders("header","header2")
//                        .allowCredentials(false).maxAge(3600);
//            }
//        };
//    }
//
//}
