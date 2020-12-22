package com.nova.ServiceAcl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.ServiceAcl.entity.Permission;

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

    // 给角色分配权限
    void saveRolePermissionRelationship(String roleId, String[] permissionId);

    // 根据Id 查看用户权限
    List<String> selectPermissionValueByUserId(String id);
}
