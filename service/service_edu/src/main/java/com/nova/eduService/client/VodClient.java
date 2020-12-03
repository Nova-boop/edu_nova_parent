package com.nova.eduService.client;

import com.nova.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-vod")
@Component
public interface VodClient {

    // 调用 nacos  根据视频ID删除阿里云视频
    @DeleteMapping("/eduVod/video/delVideo/{videoId}")
    public Result delVideo(@PathVariable("videoId") String videoId);
}
