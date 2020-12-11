package com.nova.ucenterService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.commonutils.JwtUtils;
import com.nova.commonutils.MD5;
import com.nova.servicebase.exceptionhandler.NovaException;
import com.nova.ucenterService.entity.UcenterMember;
import com.nova.ucenterService.entity.vo.RegisterVo;
import com.nova.ucenterService.mapper.UcenterMemberMapper;
import com.nova.ucenterService.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    // 注入redis
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterMember member) {

        String mobile = member.getMobile();
        String password = member.getPassword();

        // 判断非空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new NovaException(20001, "手机号或密码为空");
        }

        // 判断手机号/密码正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);

        // 手机号
        if (mobileMember == null) {
            throw new NovaException(20001, "用户未注册,请注册!!");
        }

        // 密码加密后进行比较
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new NovaException(20001, "密码错误!!");
        }
         // 判断用户是否被禁用
        if (mobileMember.getIsDisabled() == 1) {
            throw new NovaException(20001, "用户被禁用,登录失败!!");
        }
        // 登陆成功,生成token

        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    // 用户注册
    @Override
    public void register(RegisterVo registerVo) {
        // 获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();


        // 非空判断
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code) ||
                StringUtils.isEmpty(nickname)) {
            throw new NovaException(20001, "请正确输入参数");
        }

        // 验证码判断
        // 获取redis 中的验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new NovaException(20001, "验证码错误,请输入正确的验证码");
        }

        //手机号不能重复注册判断
        QueryWrapper<UcenterMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("mobile", mobile);
        Integer selectCount = baseMapper.selectCount(memberQueryWrapper);
        if (selectCount >= 1) {
            throw new NovaException(20001, "手机号已经注册,请直接登录");
        }

        // 添加
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        baseMapper.insert(member);
    }

    // 根据 openid 查询是member
    @Override
    public UcenterMember getMemberByOpenId(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);

        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }
}
