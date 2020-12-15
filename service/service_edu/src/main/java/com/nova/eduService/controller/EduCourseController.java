package com.nova.eduService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.Result;
import com.nova.commonutils.courseVo.CourseWebOrderVo;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.course.QueryCourseVo;
import com.nova.eduService.entity.frontVo.CourseWebVo;
import com.nova.eduService.entity.vo.CourseInfoVo;
import com.nova.eduService.entity.vo.CoursePublishInfoVo;
import com.nova.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
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
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduService/course")
public class EduCourseController {
    // 注入 eduCourseService
    private final EduCourseService eduCourseService;

    public EduCourseController(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }

    // 获取课程列表
    @GetMapping("/queryCourseList")
    @ApiOperation(value = "获取课程列表")
    public Result queryCourseList() {
        List<EduCourse> courseList = eduCourseService.list(null);
        return Result.ok().data("courseList", courseList);
    }

    // 条件分页查询课程列表
    @PostMapping("/queryCourseList/{current}/{limit}")
    @ApiOperation(value = "条件分页/分页查询课程列表")
    public Result pageQueryCourseList(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit", value = "当前页显示数量", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody QueryCourseVo queryCourseVo) {

        // 创建Page 对象
        Page<EduCourse> coursePage = new Page<>(current, limit);
        // 查询
        eduCourseService.pageQuery(coursePage, queryCourseVo);

        // 组织结果数据并返回
        List<EduCourse> pageRecords = coursePage.getRecords();
        long total = coursePage.getTotal();
        return Result.ok().data("total", total).data("pageRecords", pageRecords);
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

    // 删除课程
    @DeleteMapping("removeCourse/{courseId}")
    @ApiOperation(value = "删除课程")
    public Result removeCourse(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        eduCourseService.removeCourseById(courseId);
        return Result.ok();
    }


    // 根据Id 查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation(value = "根据课程ID 查询课程信息")
    public Result getCourseInfo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo", courseInfoVo);
    }


    // 修改课程信息
    @PostMapping("updateCourseInfo")
    @ApiOperation(value = "修改课程信息")
    public Result updateCourseInfo(
            @ApiParam(name = "courseInfoVo", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    // 获取发布课程信息
    @GetMapping("getPublishCourseInfo/{courseId}")
    @ApiOperation(value = "课程发布信息预览")
    public Result getPublishCourseInfo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        CoursePublishInfoVo coursePublishInfoVo = eduCourseService.publishCourseInfo(courseId);
        return Result.ok().data("coursePublishInfo", coursePublishInfoVo);
    }

    // 课程发布
    @PostMapping("publishCourse/{courseId}")
    @ApiOperation(value = "课程发布确认")
    public Result publishCourse(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    // 根据课程ID 返回课程信息 订单
    @GetMapping("getCourseInfoOrder/{courseId}")
    public CourseWebOrderVo getCourseInfoOrder(@PathVariable String courseId) {
        CourseWebVo courseWebVo = eduCourseService.selectCourseInfoById(courseId);
        CourseWebOrderVo courseWebOrderVo = new CourseWebOrderVo();
        BeanUtils.copyProperties(courseWebVo, courseWebOrderVo);
        return courseWebOrderVo;
    }
}

