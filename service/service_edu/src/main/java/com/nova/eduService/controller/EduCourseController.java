package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.vo.CourseInfoVo;
import com.nova.eduService.service.EduChapterService;
import com.nova.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@Api(description="课程管理")
@RestController
@RequestMapping("/eduService/course")
public class EduCourseController {


    // 注入 eduCourseService
    private final EduCourseService eduCourseService;

    public EduCourseController(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }

    // 添加课程
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo ){
       eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok();
    }





}

