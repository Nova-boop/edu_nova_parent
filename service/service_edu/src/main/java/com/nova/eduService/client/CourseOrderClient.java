package com.nova.eduService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-order")
public interface CourseOrderClient {

    // 判断用户是否购买课程
    @GetMapping("/eduOrder/Order/isBuyCourse/{courseId}/{userId}")
    public Boolean isBuyCourse(@PathVariable String courseId, @PathVariable String userId);
}
