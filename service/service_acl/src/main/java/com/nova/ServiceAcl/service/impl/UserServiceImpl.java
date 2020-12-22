package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.ServiceAcl.entity.User;
import com.nova.ServiceAcl.mapper.UserMapper;
import com.nova.ServiceAcl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 从数据库中取出用户信息
    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
