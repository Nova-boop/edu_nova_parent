package com.nova.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    // 上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    // 根据视频ID 删除视频
    void delVideoById(String videoId);
}
