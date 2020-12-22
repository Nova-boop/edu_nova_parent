package com.nova.ServiceAcl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.ServiceAcl.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();
}
