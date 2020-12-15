package com.nova.eduOrder.client;

import com.nova.commonutils.courseVo.CourseWebOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-edu")
@Component
public interface CourseClient {

    // 根据课程ID 返回课程信息 订单
    @GetMapping("getCourseInfoOrder/{courseId}")
    public CourseWebOrderVo getCourseInfoOrder(@PathVariable String courseId);
}
