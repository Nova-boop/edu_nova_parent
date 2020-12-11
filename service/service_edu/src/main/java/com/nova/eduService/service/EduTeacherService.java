package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduTeacher;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author nova
 * @since 2020-11-13
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> selectPopularTeachers();

    // 返回分页中的所有数据
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
