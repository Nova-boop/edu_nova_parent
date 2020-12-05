package com.nova.eduService.client;

import com.nova.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    // 调用 nacos  根据视频ID删除阿里云视频
    @DeleteMapping("/eduVod/video/delVideo/{videoId}")
    public Result delVideo(@PathVariable("videoId") String videoId);

    // 调用nacos 批量删除阿里云视频
    @DeleteMapping("/eduVod/video/delVideoList")
    public Result delVideoList(@RequestParam(value = "videoList") List<String> videoList);
}
