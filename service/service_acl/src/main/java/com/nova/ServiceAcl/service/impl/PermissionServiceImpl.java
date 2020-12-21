package com.nova.ServiceAcl.service.impl;

import com.nova.ServiceAcl.entity.Permission;
import com.nova.ServiceAcl.mapper.PermissionMapper;
import com.nova.ServiceAcl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
