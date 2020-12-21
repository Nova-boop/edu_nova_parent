package com.nova.ServiceAcl.controller;


import com.nova.ServiceAcl.service.PermissionService;
import com.nova.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("indexAllPermission")
    public Result indexAllPermission(){
        return Result.ok();
    }

}

