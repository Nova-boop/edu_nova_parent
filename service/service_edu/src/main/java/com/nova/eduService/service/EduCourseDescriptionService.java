package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduCourseDescription;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeDescription(String courseId);
}
