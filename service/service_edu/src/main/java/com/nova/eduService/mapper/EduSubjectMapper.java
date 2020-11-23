package com.nova.eduService.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.eduService.entity.EduSubject;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author nova
 * @since 2020-11-23
 */
public interface EduSubjectMapper extends BaseMapper<EduSubject> {
    List<EduSubject> selectList(QueryWrapper<Object> wrapper);
}
