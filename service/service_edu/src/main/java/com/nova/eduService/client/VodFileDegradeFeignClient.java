package com.nova.eduService.client;

import com.nova.commonutils.Result;

import java.util.List;

public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result delVideo(String videoId) {
        return Result.error().message("服务器异常 触发熔断");
    }

    @Override
    public Result delVideoList(List<String> videoList) {
        return Result.error().message("服务器异常 触发熔断");
    }
}
