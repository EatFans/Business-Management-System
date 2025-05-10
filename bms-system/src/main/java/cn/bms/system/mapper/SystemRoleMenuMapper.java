package cn.bms.system.mapper;

import cn.bms.domain.entity.SystemRoleMenu;

/**
 * 系统角色与菜单关联数据映射接口
 *
 * @author Fan
 */
public interface SystemRoleMenuMapper {
    /**
     * 添加系统角色菜单
     * @param systemRoleMenu 系统角色菜单关联数据
     * @return 结果
     */
    int addRoleMenu(SystemRoleMenu systemRoleMenu);

    /**
     * 更新系统角色菜单
     * @param systemRoleMenu 系统角色菜单关联数据
     * @return 结果
     */
    int updateRoleMenu(SystemRoleMenu systemRoleMenu);


}
