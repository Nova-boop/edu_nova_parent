package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.vo.CourseInfoVo;
import com.nova.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@Api(description = "课程管理")
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
    @ApiOperation(value = "添加课程")
    public Result addCourseInfo(
            @ApiParam(name = "courseInfoVo", value = "课程信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        // 返回课程的ID
        return Result.ok().data("courseId", id);
    }

    // 根据Id 查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation(value = "根据课程ID 查询课程信息")
    public Result getCourseInfo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfo", courseInfoVo);
    }


    // 修改课程信息
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(
            @PathVariable CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();

    }
}

