package com.nova.msmService.controller;

import com.nova.commonutils.Result;
import com.nova.msmService.service.MsmService;
import com.nova.msmService.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController //
@RequestMapping("eduMsm/msm") //
@Api(description = "短信验证服务")
public class MsmController {

    // 注入 service
    private final MsmService msmService;
    // 注入 redis 对象,设定验证码的有效时间
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public MsmController(MsmService msmService) {
        this.msmService = msmService;
    }

    // 发送短信验证码
    @GetMapping("send/{PhoneNumbers}")
    @ApiOperation(value = "阿里云短信发送")
    public Result sendMsmCode(@PathVariable String PhoneNumbers) {

        // 先从Redis 中取值,如果取到直接返回
        String code = redisTemplate.opsForValue().get(PhoneNumbers);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok();
        }

        // 如果Redis 中没有值,生成验证码

        // 生成随机值,阿里云进行发送
        code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        // 使用阿里云发送短信验证码
        Boolean isSend = msmService.sendAliMsmCode(param, PhoneNumbers);
        if (isSend) {
            // 把验证码放到Redis 中,并设置有效时间
            redisTemplate.opsForValue().set(PhoneNumbers, code, 5, TimeUnit.MINUTES);  // 有效时长为5min
            return Result.ok();

        } else {
            return Result.error();
        }

    }

}
