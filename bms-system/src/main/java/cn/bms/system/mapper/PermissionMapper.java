package cn.bms.system.mapper;

import cn.bms.domain.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 权限数据映射类
 *
 * @author Fan
 */
@Mapper
public interface PermissionMapper {

    /**
     * 通过权限id来筛选获取权限
     * @param id id
     * @return 权限信息
     */
    Permission selectPermissionById(Long id);

    /**
     * 新增一条权限数据
     * @param permission 权限信息
     * @return 结果
     */
    int insertPermission(Permission permission);

    /**
     * 更新权限信息
     * @param permission 权限信息
     * @return 结果
     */
    int updatePermission(Permission permission);

    /**
     * 检查权限是否唯一
     * @param code 权限码
     * @return 如果权限唯一就返回true，否则就返回false
     */
    int checkPermissionUnique(String code);

    /**
     * 获取全部权限
     * @return 结果
     */
    List<Permission> selectPermissions();
}
