package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.AclRole;
import com.nova.ServiceAcl.mapper.AclRoleMapper;
import com.nova.ServiceAcl.service.AclRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
@Service
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {

}
