package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduCourse;
import com.nova.eduService.entity.EduCourseDescription;
import com.nova.eduService.entity.course.QueryCourseVo;
import com.nova.eduService.entity.vo.CourseInfoVo;
import com.nova.eduService.entity.vo.CoursePublishInfoVo;
import com.nova.eduService.mapper.EduCourseMapper;
import com.nova.eduService.service.EduChapterService;
import com.nova.eduService.service.EduCourseDescriptionService;
import com.nova.eduService.service.EduCourseService;
import com.nova.eduService.service.EduVideoService;
import com.nova.servicebase.exceptionhandler.NovaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    // 注入 EduVideoService
    @Autowired
    private EduVideoService eduVideoService;

    // 注入 EduChapterService
    @Autowired
    private EduChapterService eduChapterService;


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

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int resultRows = baseMapper.updateById(eduCourse);

        if (resultRows == 0) {
            throw new NovaException(20001, "更新课程信息失败");
        }


        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        boolean b = courseDescriptionService.updateById(courseDescription);
        if (!b) {
            throw new NovaException(20001, "更新课程详情失败");
        }
    }

    // 根据课程id 查看将要发布的课程信息
    @Override
    public CoursePublishInfoVo publishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    // 条件分页查询课程列表
    @Override
    public void pageQuery(Page<EduCourse> coursePage, QueryCourseVo queryCourseVo) {

        // 组织查询条件
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();

        if (queryCourseVo == null) {
            baseMapper.selectPage(coursePage, courseQueryWrapper);
        }


        assert queryCourseVo != null;
        String title = queryCourseVo.getTitle();
        String subjectParentId = queryCourseVo.getSubjectParentId();
        String subjectId = queryCourseVo.getSubjectId();
        String teacherId = queryCourseVo.getTeacherId();

        if (!StringUtils.isEmpty(title)) {
            courseQueryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId)) {
            courseQueryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            courseQueryWrapper.eq("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            courseQueryWrapper.eq("subject_id", subjectId);
        }

        // 查询并组织返回数据
        baseMapper.selectPage(coursePage, courseQueryWrapper);

    }

    // 根据课程Id 删除课程信息
    // 删除 课程基本信息 描述信息 章节信息 小节信息
    @Override
    public void removeCourseById(String courseId) {
        EduCourse course = baseMapper.selectById(courseId);
        if (course != null && course.getIsDeleted() != 1) {

            // 删除小节
            eduVideoService.removeVideoByCourseId(courseId);
            // 删除章节
            eduChapterService.removeChapterByCourseId(courseId);
            // 删除描述
            courseDescriptionService.removeDescription(courseId);
            // 删除课程
            int deleteRows = baseMapper.deleteById(courseId);

            if (deleteRows == 0) {
                throw new NovaException(20001, "删除失败");
            }
        }
    }

}
