package com.nova.eduService.controller;

import com.nova.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(description = "用户登录")
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
public class EduLoginController {
    // 模拟登陆接口

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok()
                .data("roles","[admin]")
                .data("name", "lucy")
                .data("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png");
    }

}
