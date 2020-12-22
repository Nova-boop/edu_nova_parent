package com.nova.eduService.config;


import org.springframework.context.annotation.Configuration;

/**
 * 解决跨域
 * 在网关中实现跨域
 */

@Configuration
public class CorsConfig {

//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); //允许任何域名
//        corsConfiguration.addAllowedHeader("*"); //允许任何头
//        corsConfiguration.addAllowedMethod("*"); //允许任何方法
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); //注册
//        return new CorsFilter(source);
//    }


}