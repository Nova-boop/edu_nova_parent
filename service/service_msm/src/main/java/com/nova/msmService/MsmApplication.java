package com.nova.msmService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.nova"})  // 扫描组件
@EnableDiscoveryClient // 微服务注册注解
@EnableFeignClients  // 微服务调用注解
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
