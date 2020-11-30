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
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 添加数据到课程表
        // 将courseInfoVo 转换为eduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        // 判断是否添加成功
        int insert = baseMapper.insert(eduCourse);
        if (insert != 0) {
            String courseId = eduCourse.getId();
            EduCourseDescription courseDescription = new EduCourseDescription();
            courseDescription.setId(courseId);
            courseDescription.setDescription(courseInfoVo.getDescription());
            courseDescriptionService.save(courseDescription);
            // 返回课程ID
            return courseId;
        } else {// 添加数据到课程简介表
            throw new NovaException(20001, "课程添加失败");
        }
    }

    // 根据Id 查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        System.out.println(eduCourse);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        if (eduCourse != null) {
            // 封装数据
            BeanUtils.copyProperties(eduCourse, courseInfoVo);
            // 查询描述表
            EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
            if (courseDescription != null) {
                courseInfoVo.setDescription(courseDescription.getDescription());
            }

        }
        return courseInfoVo;
    }

    // 更新课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

    }
}
