package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.AclUser;
import com.nova.ServiceAcl.mapper.AclUserMapper;
import com.nova.ServiceAcl.service.AclUserService;
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
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

}
