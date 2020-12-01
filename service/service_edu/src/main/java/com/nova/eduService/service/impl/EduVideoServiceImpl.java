package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduVideo;
import com.nova.eduService.mapper.EduVideoMapper;
import com.nova.eduService.service.EduVideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    // 添加小节
    @Override
    public void addVideo(EduVideo eduVideo) {
        this.save(eduVideo);
    }

    // 根据课程Id 删除 小节
    // Todo 删除小节的时候删除对应的视频
    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        int deleteRows = baseMapper.delete(videoQueryWrapper);
    }
}
