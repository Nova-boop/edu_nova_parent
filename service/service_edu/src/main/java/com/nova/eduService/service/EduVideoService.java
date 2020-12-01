package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author nova
 * @since 2020-11-24
 */
public interface EduVideoService extends IService<EduVideo> {

    void addVideo(EduVideo eduVideo);

    void removeVideoByCourseId(String courseId);
}
