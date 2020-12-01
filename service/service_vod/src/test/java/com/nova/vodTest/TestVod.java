package com.nova.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

public class TestVod {

    public static void main(String[] args) throws ClientException {
        // 根据视频id 获取视频的凭证

        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GHM9cQB2PhkeeRMTNYo", "EygIqNbev1PHuRdmH96fNXhWHqMoaP");
        // 创建 request
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        // 向request 中填入 id
        request.setVideoId("cbd13a4c8a884650bdb8f6909a28003f");

        response = client.getAcsResponse(request);

        //播放凭证
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");


    }

    // 获取视频的播放地址
    private static void getPlayUrl() throws ClientException {
        //1. 根据视频id 获取视频的播放地址
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GHM9cQB2PhkeeRMTNYo", "EygIqNbev1PHuRdmH96fNXhWHqMoaP");

        // 创建 request 的response 对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();


        // 向 request 对象中设置 id
        request.setVideoId("cbd13a4c8a884650bdb8f6909a28003f");

        // 调用初始化对象的方法 传递request 获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
