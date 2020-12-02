package com.nova.vod.controller;

import com.nova.commonutils.Result;
import com.nova.vod.service.VodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduVod/video")
@Api(description = "阿里云视频服务")
public class VodController {

    private final VodService vodService;

    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    // 上传视频到阿里云
    @PostMapping("uploadAlyVideo")

    public Result uploadAlyVideo(MultipartFile file){
        String videoId = vodService.uploadVideoAly(file);

        return Result.ok().data("videoId",videoId);
    }

    // 根据视频ID 删除视频
    @DeleteMapping("delVideo/{videoId}")
    public Result delVideo(@PathVariable String videoId){
        vodService.delVideoById(videoId);
        return Result.ok();
    }

}
