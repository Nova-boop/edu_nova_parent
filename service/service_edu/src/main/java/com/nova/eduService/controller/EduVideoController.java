package com.nova.eduService.controller;


import com.alibaba.excel.util.StringUtils;
import com.nova.commonutils.Result;
import com.nova.eduService.client.VodClient;
import com.nova.eduService.entity.EduVideo;
import com.nova.eduService.service.EduVideoService;
import com.nova.servicebase.exceptionhandler.NovaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/eduService/eduVideo")
@Api(description = "小节管理")
public class EduVideoController {

    // 注入 EduVideoService
    private final EduVideoService eduVideoService;

    // 注入 VodClient interface
    private final VodClient vodClient;

    public EduVideoController(EduVideoService eduVideoService, VodClient vodClient) {
        this.eduVideoService = eduVideoService;
        this.vodClient = vodClient;
    }


    // 根据id 查询小节信息
    @PostMapping("getVideoInfo/{videoId}")
    @ApiOperation(value = "根据小节ID 查询详情")
    public Result getVideoInfo(@PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return Result.ok().data("eduVideo", eduVideo);
    }

    // 添加小节
    @PostMapping("addVideo")
    @ApiOperation(value = "添加小节")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.addVideo(eduVideo);
        return Result.ok();
    }

    // 删除小节
    @DeleteMapping("delVideo/{videoId}")
    @ApiOperation(value = "删除小节")
    public Result delVideo(@PathVariable String videoId) {

        // 删除 阿里云上视频
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        // 非空判断
        if (!StringUtils.isEmpty(videoSourceId)) {
            Result result = vodClient.delVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new NovaException(20001, "服务器异常触发熔断,删除失败!!");
            }
        }
        // 删除数据表记录
        eduVideoService.removeById(videoId);
        return Result.ok();
    }

    // 修改小节
    @PostMapping("updateVideo")
    @ApiOperation(value = "更新小节")
    public Result updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

}

