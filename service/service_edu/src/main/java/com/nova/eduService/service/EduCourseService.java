package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.vo.CourseInfoVo;

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

    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
