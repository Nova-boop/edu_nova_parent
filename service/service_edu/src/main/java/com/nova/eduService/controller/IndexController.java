package com.nova.eduService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.EduTeacher;
import com.nova.eduService.service.EduCourseService;
import com.nova.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/eduService/index")
@Api(description = "课程首页数据管理")
public class IndexController {

    // 注入service
    private final EduCourseService eduCourseService;
    private final EduTeacherService eduTeacherService;

    public IndexController(EduCourseService eduCourseService, EduTeacherService eduTeacherService) {
        this.eduCourseService = eduCourseService;
        this.eduTeacherService = eduTeacherService;
    }


    // 查询8条热门课程与4位热门讲师数据
    @GetMapping("IndexData")
    public Result index() {

        // 查询8条热门课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(courseQueryWrapper);

        // 查询4位热门讲师数据
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(teacherQueryWrapper);


        return Result.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
