package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.AclUserRole;
import com.nova.ServiceAcl.mapper.AclUserRoleMapper;
import com.nova.ServiceAcl.service.AclUserRoleService;
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
public class AclUserRoleServiceImpl extends ServiceImpl<AclUserRoleMapper, AclUserRole> implements AclUserRoleService {

}
