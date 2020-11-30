package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduChapter;
import com.nova.eduService.entity.chapter.ChapterVo;
import com.nova.eduService.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "章节管理")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    // 课程大纲列表
    @GetMapping("getChapterVideo/{courseId}")
    @ApiOperation(value = "获取chapterVideo 列表")
    public Result getChapterVideo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {

        // 根据课程ID 查询课程的章节及小节列表
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoByCourseId(courseId);

        return Result.ok().data("chapterVideoList", chapterVoList);
    }

    // 添加章节
    @PostMapping("addChapter")
    @ApiOperation(value = "添加章节")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return Result.ok();
    }

    // 根据章ID 查询chapter 信息
    @GetMapping("getChapterInfo/{chapterId}")
    @ApiOperation(value = "根据ID查询章节信息")
    public Result getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Result.ok().data("chapter", eduChapter);
    }

    // 修改chapter
    @PostMapping("updateChapter")
    @ApiOperation(value = "修改章节信息")
    public Result updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

    // 删除chapter
    @DeleteMapping("delChapter/{chapterId}")
    @ApiOperation(value = "删除章节信息")
    public Result delChapter(@PathVariable String chapterId) {
        boolean delFlag = eduChapterService.delChapter(chapterId);

        if (delFlag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

