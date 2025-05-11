package cn.bms.system.service.impl;

import cn.bms.common.exception.user.RoleIsNullException;
import cn.bms.domain.entity.SystemMenu;
import cn.bms.system.mapper.EmployeeMapper;
import cn.bms.system.mapper.SystemMenuMapper;
import cn.bms.system.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统菜单业务实现类
 *
 * @author Fan
 */
@Service
public class SystemMenuServiceImpl implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Value("${bms.superAdminRole}")
    private Long superAdmin;

    /**
     * 通过员工id来获取该员工可以使用的所有菜单
     * @param empId 员工id
     * @return 员工可以使用的菜单数据列表
     */
    @Override
    public List<SystemMenu> getMenusByEmpId(Long empId) {
        // 通过员工id来获取该员工的角色id
        Long roleId = employeeMapper.selectRoleByEmpId(empId);
        if (roleId == null || roleId == 0)
            throw new RoleIsNullException();
        // 通过员工的角色id来去查询菜单
        return getMenuByRoleId(roleId);
    }

    /**
     * 通过角色id来获取该角色可以使用的所有菜单
     * @param roleId 角色id
     * @return 该角色可以使用的所有菜单
     */
    @Override
    public List<SystemMenu> getMenuByRoleId(Long roleId) {
        // 如果角色为超级管理员，就可以获取所有的菜单
        if (roleId.equals(superAdmin)){
            return systemMenuMapper.selectAllSystemMenu();
        }
        return systemMenuMapper.selectSystemMenusByRoleId(roleId);
    }
}
