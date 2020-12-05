package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.client.VodClient;
import com.nova.eduService.entity.EduVideo;
import com.nova.eduService.mapper.EduVideoMapper;
import com.nova.eduService.service.EduVideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // 注入 vodClient interface 接口
    private final VodClient vodClient;

    public EduVideoServiceImpl(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    // 添加小节
    @Override
    public void addVideo(EduVideo eduVideo) {
        this.save(eduVideo);
    }

    // 根据课程Id 删除 小节
    @Override
    public void removeVideoByCourseId(String courseId) {
        // 删除阿里云上 video
        QueryWrapper<EduVideo> videoIdsQueryWrapper = new QueryWrapper<>();
        videoIdsQueryWrapper.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(videoIdsQueryWrapper);

        // 对象转换 11,22,33
        List<String> videoList = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoList.add(videoSourceId);
            }
        }

        if (videoList.size() > 0) {
            // 调用方法批量删除 课程下属的视频
            vodClient.delVideoList(videoList);
        }


        // 删除数据表中 video 记录
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        int deleteRows = baseMapper.delete(videoQueryWrapper);
    }
}
