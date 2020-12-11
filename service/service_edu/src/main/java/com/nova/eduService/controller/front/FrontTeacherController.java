package com.nova.eduService.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.EduTeacher;
import com.nova.eduService.service.EduCourseService;
import com.nova.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/teacherFront")
@Api(description = "前端讲师")
public class FrontTeacherController {
    // 注入 teacherService
    private final EduTeacherService teacherService;
    // 注入 courseService
    private final EduCourseService courseService;

    public FrontTeacherController(EduCourseService courseService, EduTeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    //1.分页查询讲师
    @ApiOperation(value = "条件分页查询讲师")
    @GetMapping("getTeacherPageList/{current}/{limit}")
    public Result pageQueryTeacher(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit", value = "每页显示数量", required = true)
            @PathVariable Integer limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        // 返回分页中的所有数据
        Map<String, Object> teacherMap = teacherService.getTeacherFrontList(pageTeacher);

        return Result.ok().data("teacherPageList", teacherMap);
    }

    // 查询讲师详情,实现讲师基本信息与讲师关联课程的查询
    @ApiOperation(value = "查看讲师详情")
    @GetMapping("getTeacherInfo/{teacherId}")
    public Result getTeacherInfoById(
            @ApiParam(name = "teacherId", value = "讲师Id", required = true)
            @PathVariable String teacherId) {

        // 查询讲师基本信息
        EduTeacher teacherInfo = teacherService.getById(teacherId);

        // 查询讲师ID所讲课程信息
        List<EduCourse> courseList = courseService.getCourseListByTeacherId(teacherId);

        // 返回数据
        return Result.ok().data("teacherInfo", teacherInfo).data("courseList", courseList);
    }


}
