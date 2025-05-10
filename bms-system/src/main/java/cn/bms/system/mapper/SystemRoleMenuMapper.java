package cn.bms.system.mapper;

import cn.bms.domain.entity.SystemRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色与菜单关联数据映射接口
 *
 * @author Fan
 */
@Mapper
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

    /**
     * 通过id唯一标识符来获取角色菜单关联信息
     * @param id id
     * @return 结果
     */
    SystemRoleMenu selectRoleMenuById(Long id);

    /**
     * 通过角色id来获取所有菜单信息
     * @param roleId 角色id
     * @return 菜单id列表
     */
    List<Long> selectMenusByRoleId(Long roleId);

}
