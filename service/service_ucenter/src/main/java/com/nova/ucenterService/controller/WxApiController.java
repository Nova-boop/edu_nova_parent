package com.nova.ucenterService.controller;

import com.google.gson.Gson;
import com.nova.servicebase.exceptionhandler.NovaException;
import com.nova.ucenterService.entity.UcenterMember;
import com.nova.ucenterService.service.UcenterMemberService;
import com.nova.ucenterService.utils.ConstantWxUtils;
import com.nova.ucenterService.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService ucenterMemberService;


    // 1. 生成微信扫描二维码
    @GetMapping("login")
    public String WxCode() {
        // 固定地址
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 回调地址
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new NovaException(20001, e.getMessage());
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        String state = "Nova";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名

        String qrcodeUrl = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                state);

        // 请求微信地址
        return "redirect:" + qrcodeUrl;
    }

    // 2.根据code 访问AccessToken(访问凭证) openId (身份ID)
    @GetMapping("callback")
    public String callback(String code, String state) {

        try {
            //向认证服务器发送请求换取access_token
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code);

            // 使用HttpClient 访问获取url
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            System.out.println(accessTokenInfo);

            // 解析Token 和openID
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String accessToken = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            // 使用Token 和openID 访问地址
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";

            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String userInfo = HttpClientUtils.get(userInfoUrl);

            // 解析用户信息,并存储到数据库
            HashMap userMap = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String) userMap.get("nickname");
            String headimgurl = (String) userMap.get("headimgurl");

            UcenterMember member = new UcenterMember();
            member.setNickname(nickname);
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            ucenterMemberService.save(member);

        } catch (Exception e) {
            throw new NovaException(20001, "访问出错");
        }

        return "redirect:http://localhost:3000";
    }
}
