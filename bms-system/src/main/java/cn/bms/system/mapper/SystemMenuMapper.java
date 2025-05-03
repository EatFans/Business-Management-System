package cn.bms.system.mapper;

import cn.bms.domain.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;

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
}
