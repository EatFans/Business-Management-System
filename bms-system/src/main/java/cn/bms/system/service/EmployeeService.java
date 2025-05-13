package cn.bms.system.service;

import cn.bms.domain.entity.Employee;
import cn.bms.domain.entity.EmployeeRole;
import cn.bms.domain.entity.Role;

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

    /**
     * 通过员工用户的id来查找用户
     * @param id id
     * @return 员工用户实体
     */
    Employee selectEmployeeById(Long id);

    /**
     * 添加一个员工用户
     * @param employee 员工
     * @return 添加成功就返回true，否则就返回false
     */
    boolean addEmployee(Employee employee);

    /**
     * 更新员工用户
     * @param employee 员工
     * @return 更新成功就返回true，否则就返回false
     */
    boolean updateEmployee(Employee employee);

    /**
     * 生成工号
     * @return 返回生成好的工号
     */
    String generateJobNumber();

    /**
     * 检查工号（用户名）是否唯一
     * @param employee 员工
     * @return 如果唯一就返回true，否则就返回false
     */
    boolean checkJobNumberUnique(Employee employee);

    /**
     * 检查员工邮箱是否唯一
     * @param employee 员工
     * @return 如果唯一就返回true，否则就就返回false
     */
    boolean checkEmailUnique(Employee employee);

    /**
     * 检查员工手机号是否唯一
     * @param employee 员工
     * @return 如果唯一就返回true，否则就返回false
     */
    boolean checkPhoneNumberUnique(Employee employee);

    /**
     * 分配角色
     */
    boolean assignRole(EmployeeRole employeeRole);

    /**
     * 给员工分配默认角色
     * @param empId 员工id
     * @return 如果分配成功就返回true，否则就返回false
     */
    boolean assignDefaultRole(Long empId);

    /**
     * 更新用户角色
     * @param employeeRole 用户角色关联表
     * @return 更新成功就返回true，否则就返回false
     */
    boolean updateRole(EmployeeRole employeeRole);

    /**
     * 获取角色
     * @param empId 员工id
     * @return 角色
     */
    Role getRole(Long empId);
}
