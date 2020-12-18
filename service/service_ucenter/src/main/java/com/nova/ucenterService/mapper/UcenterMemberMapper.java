package com.nova.ucenterService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.ucenterService.entity.UcenterMember;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author nova
 * @since 2020-12-08
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    // 查看注册人数
    Integer registerCount(String day);
}
