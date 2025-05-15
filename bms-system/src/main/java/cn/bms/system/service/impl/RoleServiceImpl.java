package cn.bms.system.service.impl;

import cn.bms.domain.entity.Role;
import cn.bms.system.mapper.RoleMapper;
import cn.bms.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现类
 *
 * @author Fan
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 添加角色
     * @param role 角色
     * @return 如果添加成功就返回true，否则就返回false
     */
    @Override
    public boolean addRole(Role role) {
        int i = roleMapper.insertRole(role);
        return i > 0;
    }

    /**
     * 更新角色
     * @param role 角色
     * @return 如果更新成功就返回true，否则就返回false
     */
    @Override
    public boolean updateRole(Role role) {
        int i = roleMapper.updateRole(role);
        return i > 0;
    }

    /**
     * 通过id来查询角色
     * @param roleId 角色id
     * @return 角色
     */
    @Override
    public Role selectRoleById(Long roleId) {
        if (roleId != null && roleId != 0)
            return roleMapper.selectRoleById(roleId);
        return null;
    }

    /**
     * 查询角色列表
     * @return 角色列表
     */
    @Override
    public List<Role> selectRoles() {
        return roleMapper.selectRoles();
    }

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 如果删除成功就返回true，否则就返回false
     */
    @Override
    public boolean deleteRole(Long roleId) {
        // TODO: 先删除关联表的数据，再删除role表的数据
        return false;
    }

    /**
     * 检查角色是否唯一
     * @param name 名字
     * @param key 关键字
     * @return 如果唯一就返回true，否则就返回false
     */
    @Override
    public boolean checkRoleUnique(String name, String key) {
        int i = roleMapper.checkRoleUnique(name, key);
        return i <= 0;
    }
}
