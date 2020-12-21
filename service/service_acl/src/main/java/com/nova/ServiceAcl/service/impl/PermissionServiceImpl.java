package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.Permission;
import com.nova.ServiceAcl.mapper.PermissionMapper;
import com.nova.ServiceAcl.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // 使用递归封装数据的方法- 获取递归入口
    public static List<Permission> buildList(List<Permission> permissionList) {
        // 创建list 集合 封装最终的数据
        List<Permission> finalNode = new ArrayList<>();

        // 遍历 permissionList 得到pid 为0 设置 level 为1
        for (Permission permissionNode : permissionList) {
            if (permissionNode.getPid().equals("0")) {
                permissionNode.setLevel(1);
                // 根据顶层菜单,查询子菜单,并封装数据
                finalNode.add(selectChildren(permissionNode, permissionList));
            }
        }
        return finalNode;
    }

    // 使用递归封装数据的方法 - 根据顶层菜单,查询子菜单,并封装数据
    private static Permission selectChildren(Permission permissionNode, List<Permission> permissionList) {

        // 向上级菜单中封装下一级菜单
        permissionNode.setChildren(new ArrayList<>());

        // 遍历所有菜单集合 与当前数据进行比较
        for (Permission permission : permissionList) {
            if (permissionNode.getId().equals(permission.getPid())) {
                // 二级层级
                int level = permissionNode.getLevel() + 1;
                permission.setLevel(level);
                // 把子菜单放到父级菜单中
                permissionNode.getChildren().add(selectChildren(permission, permissionList));
            }
        }
        return permissionNode;
    }

    // 查询所有权限列表
    @Override
    public List<Permission> getAllPermission() {

        // 查询所有权限
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Permission> permissionList = baseMapper.selectList(wrapper);

        // 封装数据
        List<Permission> resultList = buildList(permissionList);
        return resultList;
    }


}
