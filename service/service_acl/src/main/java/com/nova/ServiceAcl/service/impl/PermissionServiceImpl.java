package com.nova.ServiceAcl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.ServiceAcl.entity.Permission;
import com.nova.ServiceAcl.entity.RolePermission;
import com.nova.ServiceAcl.entity.User;
import com.nova.ServiceAcl.mapper.PermissionMapper;
import com.nova.ServiceAcl.service.PermissionService;
import com.nova.ServiceAcl.service.RolePermissionService;
import com.nova.ServiceAcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserService userService;


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

    // 使用递归封装数据的方法- 获取递归入口
    private List<Permission> buildList(List<Permission> permissionList) {
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

    // 递归删除菜单
    @Override
    public void removeChildBv(String permissionId) {

        // 创建lIST 集合 用于存储删除记录的id
        List<String> idList = new ArrayList<>();

        // 向集合中设置删除的记录的id
        this.selectPermissionChildById(permissionId, idList);
        // 封装本机菜单的id
        idList.add(permissionId);
        // 删除
        baseMapper.deleteBatchIds(idList);
    }

    // 根据id ,查询子菜单的id,封装到集合中
    private void selectPermissionChildById(String id, List<String> idList) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        wrapper.select("id");
        List<Permission> childIdList = baseMapper.selectList(wrapper);

        // 获取childIdList中的子菜单的id值,封装到idList中
        childIdList.forEach(item -> {
            // 添加当前的子菜单的id到list集合中
            idList.add(item.getId());
            // 递归调用
            this.selectPermissionChildById(item.getId(), idList);
        });
    }

    // 给角色分配权限
    @Override
    public void saveRolePermissionRelationship(String roleId, String[] permissionIds) {
        List<RolePermission> permissionList = new ArrayList<>();

        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            permissionList.add(rolePermission);
        }
        rolePermissionService.saveBatch(permissionList);
    }

    //根据用户id获取用户菜单
    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        List<String> selectPermissionValueList = null;
        if (this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }
    

    /**
     * 判断用户是否系统管理员
     *
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        if (null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }
}
