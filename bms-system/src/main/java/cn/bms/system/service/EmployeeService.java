package cn.bms.system.service;

import cn.bms.domain.entity.Employee;

/**
 * 员工业务接口类
 *
 * @author Fan
 */
public interface EmployeeService {

    /**
     * 通过用户名来查找员工用户
     * @param username 用户名
     * @return 员工用户实体
     */
    Employee selectEmployeeByUserName(String username);
}
