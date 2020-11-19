package com.nova.eduService.controller;

import com.nova.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(description = "用户登录")
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin // 跨域注解
public class EduLoginController {
    // 模拟登陆接口

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","adminSa");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","admin").data("name", "lucy").data("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png");
    }

}
