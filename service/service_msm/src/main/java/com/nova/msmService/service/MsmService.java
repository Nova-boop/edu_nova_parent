package com.nova.msmService.service;


import java.util.Map;

public interface MsmService {
    // 使用阿里云发送短信验证码
    Boolean sendAliMsmCode(Map<String, Object> param, String PhoneNumbers);
}
