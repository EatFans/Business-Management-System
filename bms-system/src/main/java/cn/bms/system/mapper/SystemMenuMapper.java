package cn.bms.system.mapper;

import cn.bms.domain.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单数据映射类
 *
 * @author Fan
 */
@Mapper
public interface SystemMenuMapper {

    /**
     * 通过菜单id来查找
     * @param menuId 菜单id
     * @return 结果
     */
    SystemMenu selectSystemMenuById(Long menuId);

    /**
     * 插入一条菜单数据
     * @param systemMenu 菜单数据
     * @return 结果
     */
    int insertSystemMenu(SystemMenu systemMenu);

    /**
     * 更新菜单数据
     * @param systemMenu 菜单数据
     * @return 结果
     */
    int updateSystemMenu(SystemMenu systemMenu);

    /**
     * 通过角色id来获取该角色所有的菜单信息
     * @param roleId 角色id
     * @return 结果
     */
    List<SystemMenu> selectSystemMenusByRoleId(Long roleId);

    /**
     * 获取所有的菜单
     * @return 结果
     */
    List<SystemMenu> selectAllSystemMenu();
}
