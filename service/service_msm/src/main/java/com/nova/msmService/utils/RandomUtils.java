package com.nova.msmService.utils;

public class RandomUtils {

    // 生成6位短信验证码
    public static String generateCode() {
        long codeL = System.nanoTime();
        String codeStr = Long.toString(codeL);
        String verifyCode = codeStr.substring(codeStr.length() - 6);
        return verifyCode;
    }

}
