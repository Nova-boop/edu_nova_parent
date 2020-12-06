package com.nova.eduService.client;

import com.nova.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
<<<<<<< HEAD
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result delVideo(String videoId) {
        return Result.error().message("服务器异常 触发熔断");
    }

    @Override
    public Result delVideoList(List<String> videoList) {
        return Result.error().message("服务器异常 触发熔断");
=======
public class VodFileDegradeFeignClient  implements VodClient{

    // 删除一个视频
    @Override
    public Result delVideo(String videoId) {
        return Result.error().message("删除视频出错,服务器异常触发熔断");
    }

    // 删除多个视频
    @Override
    public Result delVideoList(List<String> videoList) {
        return Result.error().message("删除视频出错,服务器异常触发熔断");
>>>>>>> origin/main
    }
}
