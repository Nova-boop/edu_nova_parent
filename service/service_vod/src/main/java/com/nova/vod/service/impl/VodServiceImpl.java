package com.nova.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.nova.servicebase.exceptionhandler.NovaException;
import com.nova.vod.service.VodService;
import com.nova.vod.utils.ConstantVodUtils;
import com.nova.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {


    // 上传视频到阿里云
    @Override
    public String uploadVideoAly(MultipartFile file) {

        try {
            // 获取文件的原始名称
            String fileName = file.getOriginalFilename();
            // 获取文件名
            // String title = fileName.split("\\.")[0];
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;

            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            // 返回 videoId
            return videoId;
        } catch (Exception e) {
            // 打印调试
            e.printStackTrace();
            return null;
        }
    }

    // 根据视频ID 删除视频
    @Override
    public void delVideoById(String videoId) {

        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new NovaException(20001, "删除失败!!!!");
        }

    }

    // 批量删除 阿里云视频
    @Override
    public void delVideoList(List videoList) {

        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 转换数据结构 1,2,3,4
            String videoIds = StringUtils.join(videoList.toArray(), ",");
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new NovaException(20001, "删除失败!!!!");
        }

    }

    // 根据视频ID 获取视频的播放凭证
    @Override
    public String getPlayAuth(String videoId) {
        try {
            // 初始化客户端
            DefaultAcsClient client = InitVodClient.initVodClient(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET);

            // 获取响应
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            // 返回播放凭证
            String playAuth = response.getPlayAuth();
            return playAuth;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NovaException(20001, "获取失败!!");
        }

    }
}
