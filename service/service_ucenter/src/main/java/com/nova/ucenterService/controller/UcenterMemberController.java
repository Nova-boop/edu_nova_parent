package com.nova.ucenterService.controller;


import com.nova.commonutils.JwtUtils;
import com.nova.commonutils.Result;
import com.nova.commonutils.userVo.UcenterMemberVo;
import com.nova.ucenterService.entity.UcenterMember;
import com.nova.ucenterService.entity.vo.RegisterVo;
import com.nova.ucenterService.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/ucService/uCenterMember")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    // 用户登录
    @PostMapping("login")
    public Result loginUser(@RequestBody UcenterMember ucenterMember) {
        String token = ucenterMemberService.login(ucenterMember);
        return Result.ok().data("token", token);
    }

    // 用户注册
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return Result.ok();
    }

    // 根据token 获取用户信息
    @GetMapping("getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 根据用户ID 获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        return Result.ok().data("userInfo", member);
    }

    // 根据用户ID 获取用户信息
    @GetMapping("getUserInfo/{userId}")
    public UcenterMemberVo getUserInfo(@PathVariable String userId) {
        UcenterMember member = ucenterMemberService.getById(userId);
        UcenterMemberVo memberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member, memberVo);
        return memberVo;
    }

    // 查看某一天的注册人数
    @GetMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day) {
        Integer count = ucenterMemberService.registerCount(day);
        return Result.ok().data("registerCount", count);
    }
}

