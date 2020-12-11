package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduTeacher;
import com.nova.eduService.mapper.EduTeacherMapper;
import com.nova.eduService.service.EduTeacherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-11-13
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    // 热门讲师信息
    @Cacheable(key = "'popularTeacherList'", value = "index")
    @Override
    public List<EduTeacher> selectPopularTeachers() {

        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> popularTeacherList = baseMapper.selectList(teacherQueryWrapper);

        return popularTeacherList;
    }

    // 返回分页中的所有数据
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("level");
        baseMapper.selectPage(pageTeacher, queryWrapper);


        List<EduTeacher> records = pageTeacher.getRecords();

        // 获取分页数据放到map 中，返回map
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean hasNext = pageTeacher.hasNext();
        boolean hasPrevious = pageTeacher.hasPrevious();

        HashMap<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        // 返回数据

        return map;
    }
}
