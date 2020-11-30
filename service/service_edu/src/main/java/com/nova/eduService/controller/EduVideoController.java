package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduVideo;
import com.nova.eduService.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EduVideoService eduVideoService;

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

    // 删除小节  需要再完善
    @DeleteMapping("delVideo/{videoId}")
    @ApiOperation(value = "删除小节")
    public Result delVideo(@PathVariable String videoId) {
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

