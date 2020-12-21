package com.nova.ServiceAcl.service.impl;

import com.nova.ServiceAcl.entity.Role;
import com.nova.ServiceAcl.mapper.RoleMapper;
import com.nova.ServiceAcl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
