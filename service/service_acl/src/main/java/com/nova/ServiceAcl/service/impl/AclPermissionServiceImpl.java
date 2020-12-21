package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.AclPermission;
import com.nova.ServiceAcl.mapper.AclPermissionMapper;
import com.nova.ServiceAcl.service.AclPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
@Service
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements AclPermissionService {

}
