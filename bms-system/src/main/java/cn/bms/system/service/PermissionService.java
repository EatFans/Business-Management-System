package cn.bms.system.service;

import cn.bms.domain.entity.Permission;

/**
 * 权限业务接口类
 *
 * @author Fan
 */
public interface PermissionService {

    /**
     * 通过权限id获取权限信息
     * @param id id
     * @return 权限信息
     */
    Permission selectPermissionById(Long id);

    /**
     * 添加权限
     * @param permission 权限信息
     * @return 如果添加成功就返回true，否则就返回false
     */
    boolean addPermission(Permission permission);

    /**
     * 更新权限
     * @param permission 权限信息
     * @return 如果更新成功就返回true，否则就返回false
     */
    boolean updatePermission(Permission permission);
}
