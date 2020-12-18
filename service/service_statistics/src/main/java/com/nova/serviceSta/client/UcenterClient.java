package com.nova.serviceSta.client;

import com.nova.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    // 查看某一天的注册人数
    @GetMapping("/ucService/uCenterMember/registerCount/{day}")
    public Result registerCount(@PathVariable("day") String day);
}
