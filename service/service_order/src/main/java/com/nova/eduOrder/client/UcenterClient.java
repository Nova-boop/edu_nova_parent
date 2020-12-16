package com.nova.eduOrder.client;


import com.nova.commonutils.userVo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-ucenter")
@Component
public interface UcenterClient {

    // 根据用户ID 获取用户信息
    @GetMapping("/ucService/uCenterMember/getUserInfo/{userId}")
    public UcenterMemberVo getUserInfo(@PathVariable("userId") String userId);

}
