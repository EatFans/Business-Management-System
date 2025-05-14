package cn.bms.system.service.impl;

import cn.bms.domain.entity.Permission;
import cn.bms.system.mapper.PermissionMapper;
import cn.bms.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限业务实现类
 *
 * @author Fan
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过权限id获取权限信息
     * @param id id
     * @return 权限信息
     */
    @Override
    public Permission selectPermissionById(Long id) {
        if (id != null && id != 0){
            return permissionMapper.selectPermissionById(id);
        }
        return null;
    }

    /**
     * 添加权限
     * @param permission 权限信息
     * @return 如果成功添加就返回true，否则就返回false
     */
    @Override
    public boolean addPermission(Permission permission) {
        int flag = permissionMapper.insertPermission(permission);
        return flag > 0;
    }

    /**
     * 更新权限
     * @param permission 权限信息
     * @return 如果成功更新就返回true，否则就返回false
     */
    @Override
    public boolean updatePermission(Permission permission) {
        int flag = permissionMapper.updatePermission(permission);
        return flag > 0;
    }

    /**
     * 检查权限是否唯一
     * @param code 权限码
     * @return 如果唯一就返回true，否则就返回false
     */
    @Override
    public boolean checkPermissionUnique(String code) {
        int count = permissionMapper.checkPermissionUnique(code);
        return count <= 0;
    }

    /**
     * 获取全部的权限
     * @return 结果
     */
    @Override
    public List<Permission> selectPermission() {
        return permissionMapper.selectPermissions();
    }
}

