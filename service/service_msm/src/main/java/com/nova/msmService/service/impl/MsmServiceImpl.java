package com.nova.msmService.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.nova.msmService.service.MsmService;
import com.nova.servicebase.exceptionhandler.NovaException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    // 使用阿里云发送短信验证码
    @Override
    public Boolean sendAliMsmCode(Map<String, Object> param, String PhoneNumbers) {

        // 初始化client
        if (StringUtils.isEmpty(PhoneNumbers)) return false;
        DefaultProfile profile =
                DefaultProfile.getProfile(
                        "cn-hangzhou",
                        "LTAI4GHM9cQB2PhkeeRMTNYo",
                        "EygIqNbev1PHuRdmH96fNXhWHqMoaP");
        IAcsClient client = new DefaultAcsClient(profile);

        // 设置相关参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        // 设置发送相关参数
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", "我的Nova在线教育网站");
        request.putQueryParameter("TemplateCode", "SMS_206552350");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));  // 转换为json 数据字符串

        try {
            // 实现发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;

        } catch (Exception e) {
            e.printStackTrace();
            throw new NovaException(20001, "短信验证码发送失败");
        }
    }
}
