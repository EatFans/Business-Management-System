package cn.bms.system.service;

import cn.bms.domain.entity.Role;

import java.util.List;

/**
 * 角色业务接口类
 *
 * @author Fan
 */
public interface RoleService {

    /**
     * 添加角色
     * @param role 角色
     * @return 如果添加成功就返回true，否则就返回false
     */
    boolean addRole(Role role);

    /**
     * 更新角色
     * @param role 角色
     * @return 如果更新成功就返回true，否则就返回false
     */
    boolean updateRole(Role role);

    /**
     * 通过id来查询角色
     * @param roleId 角色id
     * @return 角色
     */
    Role selectRoleById(Long roleId);

    /**
     * 查询所有角色列表
     * @return 角色列表
     */
    List<Role> selectRoles();

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 如果成功删除就返回true，否则就返回false
     */
    boolean deleteRole(Long roleId);

    /**
     * 检查角色是否唯一
     * @param name 名字
     * @param key 关键字
     * @return 如果唯一就返回true，否则就返回false
     */
    boolean checkRoleUnique(String name, String key);
}
