package com.nova.eduService.service;

import com.nova.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
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

    void saveCourseInfo(CourseInfoVo courseInfoVo);
}
