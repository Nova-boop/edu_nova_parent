package com.nova.ServiceAcl.controller;


import com.nova.ServiceAcl.entity.Permission;
import com.nova.ServiceAcl.service.PermissionService;
import com.nova.commonutils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/ServiceAcl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // 查询所有权限列表
    @ApiOperation(value = "查询所有权限列表")
    @GetMapping("getAllPermission")
    public Result indexAllPermission() {
        List<Permission> list = permissionService.getAllPermission();
        return Result.ok().data("children", list);
    }

    // 递归删除菜单
    @ApiOperation(value = "删除权限及子权限")
    @DeleteMapping("removePermission/{permissionId}")
    public Result removePermission(@PathVariable String permissionId) {
        permissionService.removeChildBv(permissionId);
        return Result.ok();
    }

    // 给角色分配权限
    @ApiOperation(value = "给角色分配权限")
    @PostMapping("doAssign")
    public Result rolePermission(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRelationship(roleId,permissionId);
        return Result.ok();
    }

}

