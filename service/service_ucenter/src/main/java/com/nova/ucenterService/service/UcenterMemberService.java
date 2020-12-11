package com.nova.ucenterService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.ucenterService.entity.UcenterMember;
import com.nova.ucenterService.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author nova
 * @since 2020-12-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    // 用户登录
    String login(UcenterMember ucenterMember);

    // 用户注册
    void register(RegisterVo registerVo);

    // 根据 openid 查询是member
    UcenterMember getMemberByOpenId(String openid);
}
