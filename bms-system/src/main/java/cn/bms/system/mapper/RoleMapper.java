package cn.bms.system.mapper;

import cn.bms.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色数据映射接口类
 *
 * @author Fan
 */
@Mapper
public interface RoleMapper {

    /**
     * 通过角色id来查找角色数据
     * @param roleId 角色id
     * @return 角色信息
     */
    Role selectRoleById(Long roleId);

    /**
     * 通过角色名来查找角色数据
     * @param roleName 角色名
     * @return 角色信息
     */
    Role selectRoleByName(String roleName);

    /**
     * 通过角色key来查找角色数据
     * @param roleKey 角色key
     * @return 角色信息
     */
    Role selectRoleByKey(String roleKey);

    /**
     * 插入角色信息
     * @param role 角色
     * @return 结果
     */
    int insertRole(Role role);

    /**
     * 更新角色信息
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(Role role);

    /**
     * 查询所有的角色
     * @return 角色列表
     */
    List<Role> selectRoles();

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 结果
     */
    int deleteRole(Long roleId);

    /**
     * 检查角色是否唯一
     * @param name 角色名
     * @param key 角色关键字
     * @return 结果
     */
    int checkRoleUnique(@Param("name") String name, @Param("key") String key);
}
