package com.nova.eduService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.frontVo.CourseWebVo;
import com.nova.eduService.entity.vo.CoursePublishInfoVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    // 课程发布
    CoursePublishInfoVo getPublishCourseInfo(String courseId);

    // 查询课程信息
    CourseWebVo selectInfoWebById(String courseId);
}
