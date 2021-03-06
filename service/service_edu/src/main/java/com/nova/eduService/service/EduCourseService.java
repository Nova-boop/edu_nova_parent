package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.course.QueryCourseVo;
import com.nova.eduService.entity.frontVo.CourseFrontVo;
import com.nova.eduService.entity.frontVo.CourseWebVo;
import com.nova.eduService.entity.vo.CourseInfoVo;
import com.nova.eduService.entity.vo.CoursePublishInfoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
public interface EduCourseService extends IService<EduCourse> {
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    // 根据Id 查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    // 更新课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    // 根据课程id 查看将要发布的课程信息
    CoursePublishInfoVo publishCourseInfo(String courseId);

    // 条件分页查询 课程列表
    void pageQuery(Page<EduCourse> coursePage, QueryCourseVo queryCourseVo);

    // 删除课程
    void removeCourseById(String courseId);

    // 查询热门课程
    List<EduCourse> selectPopularCourses();

    // 查询讲师ID所讲课程信息
    List<EduCourse> getCourseListByTeacherId(String teacherId);

    // 条件分页查询课程列表
    List<EduCourse> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    // 根据课程ID 查询课程和讲师
    CourseWebVo selectCourseInfoById(String courseId);
}
