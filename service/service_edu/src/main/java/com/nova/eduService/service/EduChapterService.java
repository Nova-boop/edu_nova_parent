package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduChapter;
import com.nova.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean delChapter(String chapterId);
}
