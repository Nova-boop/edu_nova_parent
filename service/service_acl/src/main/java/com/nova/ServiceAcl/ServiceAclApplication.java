package com.nova.ServiceAcl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nova"})  // 扫描组件
@MapperScan("com.nova.ServiceAcl.mapper")
@EnableDiscoveryClient // 微服务注册注解
@EnableFeignClients  // 微服务调用注解
public class ServiceAclApplication {
}
