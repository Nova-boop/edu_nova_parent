package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.AclRolePermission;
import com.nova.ServiceAcl.mapper.AclRolePermissionMapper;
import com.nova.ServiceAcl.service.AclRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
@Service
public class AclRolePermissionServiceImpl extends ServiceImpl<AclRolePermissionMapper, AclRolePermission> implements AclRolePermissionService {

}
