package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.chapter.ChapterVo;
import com.nova.eduService.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/eduService/eduChapter")
@Api(description = "课程章节")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    // 课程大纲列表
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(
            @ApiParam(name = "courseId",value = "课程ID",required = true)
            @PathVariable String courseId){

        // 根据课程ID 查询课程的章节及小节列表
        List<ChapterVo> chapterVoList= eduChapterService.getChapterVideoByCourseId(courseId);

        return Result.ok().data("chapterVoList",chapterVoList);
    }

    // 添加章节
    @PostMapping("addChapter")
    public Result addChapter(){

        return Result.ok();
    }

}

