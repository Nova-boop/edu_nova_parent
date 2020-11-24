package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.EduCourseDescription;
import com.nova.eduService.entity.vo.CourseInfoVo;
import com.nova.eduService.mapper.EduCourseMapper;
import com.nova.eduService.service.EduCourseDescriptionService;
import com.nova.eduService.service.EduCourseService;
import com.nova.servicebase.exceptionhandler.NovaException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    // 注入 eduCourseDescriptionService
    private final EduCourseDescriptionService courseDescriptionService;

    public EduCourseServiceImpl(EduCourseDescriptionService courseDescriptionService) {
        this.courseDescriptionService = courseDescriptionService;
    }

    // 添加课程基本信息的方法
    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {

        // 添加数据到课程表
        // 将courseInfoVo 转换为eduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        // 判断是否添加成功
        int insert = baseMapper.insert(eduCourse);
        if (insert != 0) {// 添加数据到课程简介表
            String courseId = eduCourse.getId();
            EduCourseDescription courseDescription = new EduCourseDescription();
            courseDescription.setId(courseId);
            courseDescription.setDescription(courseInfoVo.getDescription());
            courseDescriptionService.save(courseDescription);
        } else {
            // 添加失败
            throw new NovaException(20001, "课程添加失败");
        }
    }
}
