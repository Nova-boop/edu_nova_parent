package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduCourseDescription;
import com.nova.eduService.mapper.EduCourseDescriptionMapper;
import com.nova.eduService.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    // 根据课程ID 删除课程描述
    @Override
    public void removeDescription(String courseId) {
        int deleteRows = baseMapper.deleteById(courseId);
    }
}
