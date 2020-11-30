package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduChapter;
import com.nova.eduService.entity.EduVideo;
import com.nova.eduService.entity.chapter.ChapterVo;
import com.nova.eduService.entity.chapter.VideoVo;
import com.nova.eduService.mapper.EduChapterMapper;
import com.nova.eduService.service.EduChapterService;
import com.nova.eduService.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    // 注入video 的service
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        // 获取章列表
        QueryWrapper<EduChapter> chapterVoQueryWrapper = new QueryWrapper<>();

        chapterVoQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(chapterVoQueryWrapper);

        // 获取节列表
        QueryWrapper<EduVideo> videoVoQueryWrapper = new QueryWrapper<>();
        videoVoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideos = eduVideoService.list(videoVoQueryWrapper);

        // 组织返回数据
        ArrayList<ChapterVo> chapterVideoList = new ArrayList<>();
        // 将章列表添加到结果数据集中
        for (EduChapter eduChapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            chapterVideoList.add(chapterVo);


            // 将小节列表添加到结果数据集中
            ArrayList<VideoVo> videoVoList = new ArrayList<>();
            for (EduVideo eduVideo : eduVideos) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            // 将小节数据封装到章
            chapterVo.setChildren(videoVoList);
        }

        return chapterVideoList;
    }
}
