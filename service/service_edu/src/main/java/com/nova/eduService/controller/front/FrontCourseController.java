package com.nova.eduService.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.frontVo.CourseFrontVo;
import com.nova.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eduService/courseFront")
@Api(description = "前端课程")
public class FrontCourseController {
    // 注入service
    private final EduCourseService courseService;

    public FrontCourseController(EduCourseService courseService) {
        this.courseService = courseService;
    }

    //条件分页查询课程列表
    @PostMapping("getCourseFrontList/{current}/{limit}")
    public Result getCourseFrontPageList(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit", value = "每页显示数量", required = true)
            @PathVariable Integer limit,
            @RequestBody(required = false) CourseFrontVo courseFrontVo) {

        // 分页查询
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        List<EduCourse> courseFrontList = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        return Result.ok().data("courseFrontList", courseFrontList);
    }

    // 根据课程ID 查询课程详情,返回讲师信息,课程详情,课程描述,章节列表
    @GetMapping("getCourseFrontInfo/{courseId}")
    public Result getCourseFrontPageList(@PathVariable String courseId) {

        return Result.ok();
    }


}
