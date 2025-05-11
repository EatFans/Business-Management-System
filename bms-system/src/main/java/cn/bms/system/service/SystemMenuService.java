package cn.bms.system.service;

import cn.bms.domain.entity.SystemMenu;

import java.util.List;

/**
 * 系统菜单业务接口类
 *
 * @author Fan
 */
public interface SystemMenuService {
    /**
     * 通过员工id来获取该员工可以使用的所有菜单
     * @param empId 员工id
     * @return 员工可以使用的菜单数据列表
     */
    List<SystemMenu> getMenusByEmpId(Long empId);

    /**
     * 通过角色id来获取该角色可以使用的所有菜单
     * @param roleId 角色id
     * @return 该角色可以使用的菜单数据列表
     */
    List<SystemMenu> getMenuByRoleId(Long roleId);
}
