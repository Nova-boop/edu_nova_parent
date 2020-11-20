package com.nova.ossService.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.nova.ossService.service.FileService;
import com.nova.ossService.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    // 实现上传接口
    @Override
    public String upload(MultipartFile file) {

        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String uploadUrl = null;


        try {
            // 创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 上传文件流
            InputStream inputStream = file.getInputStream();
            // 获取文件名
            String filename = file.getOriginalFilename();

            // 添加uuid随机字符串避免文件被覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String newName =uuid + filename ;

            // 构建日期路径
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/" + newName;

            // 实现文件上传
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient
            ossClient.shutdown();

            // 拼接获取文件的url
            // https://edu-nova.oss-cn-chengdu.aliyuncs.com/01.jpg
            uploadUrl = "https://" + bucketName + "." + endPoint + "/" + filename;
        } catch (Exception ignored) {
        }
        // 返回结果
        return uploadUrl;

    }
}
