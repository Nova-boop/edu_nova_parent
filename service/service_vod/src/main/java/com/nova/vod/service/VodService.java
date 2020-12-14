package com.nova.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    // 上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    // 根据视频ID 删除视频
    void delVideoById(String videoId);

    // 批量删除 阿里云视频
    void delVideoList(List videoList);

    // 根据视频ID 获取视频的播放凭证
    String getPlayAuth(String videoId);
}
