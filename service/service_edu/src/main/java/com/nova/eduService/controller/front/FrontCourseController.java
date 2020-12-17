package com.nova.eduService.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.JwtUtils;
import com.nova.commonutils.Result;
import com.nova.eduService.client.CourseOrderClient;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.chapter.ChapterVo;
import com.nova.eduService.entity.frontVo.CourseFrontVo;
import com.nova.eduService.entity.frontVo.CourseWebVo;
import com.nova.eduService.service.EduChapterService;
import com.nova.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/eduService/courseFront")
@Api(description = "前端课程")
public class FrontCourseController {
    // 注入service
    private final EduCourseService courseService;
    private final EduChapterService chapterService;

    public FrontCourseController(EduCourseService courseService, EduChapterService chapterService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @Autowired
    private CourseOrderClient courseOrderClient;

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
    public Result getCourseFrontPageList(@PathVariable String courseId,
                                         HttpServletRequest request) {

        // 根据课程ID 查询课程和讲师
        CourseWebVo courseWebVo = courseService.selectCourseInfoById(courseId);
        // 查询章节
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        // 返回购买状态
        Boolean isBuyCourse = courseOrderClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        // 返回结果数据
        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVoList", chapterVoList).data("isBuyCourse", isBuyCourse);
    }
}
