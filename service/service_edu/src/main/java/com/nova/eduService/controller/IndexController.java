package com.nova.eduService.controller;

import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.EduTeacher;
import com.nova.eduService.service.EduCourseService;
import com.nova.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.cache.annotation.Cacheable;
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
        List<EduCourse> popularCoursesList = eduCourseService.selectPopularCourses();

        // 查询4位热门讲师数据
        List<EduTeacher> popularTeacherList = eduTeacherService.selectPopularTeachers();
        // 返回数据
        return Result.ok().data("popularCoursesList", popularCoursesList).data("popularTeacherList", popularTeacherList);
    }
}
