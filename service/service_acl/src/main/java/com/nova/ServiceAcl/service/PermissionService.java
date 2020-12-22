package com.nova.ServiceAcl.service;

import com.nova.ServiceAcl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
public interface PermissionService extends IService<Permission> {

    // 查询所有权限列表
    List<Permission> getAllPermission();

    // 递归删除菜单
    void removeChildBv(String permissionId);
}
