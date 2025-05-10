package cn.bms.system.mapper;

import cn.bms.domain.entity.Employee;
import cn.bms.domain.entity.EmployeeRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工数据映射接口类
 *
 * @author Fan
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 通过用户名查询员工
     * @param userName 用户名
     * @return 用户对象信息
     */
    Employee selectEmployeeByUserName(String userName);

    /**
     * 通过员工主键id查询用户
     * @param empId 员工id
     * @return 员工对象信息
     */
    Employee selectEmployeeById(Long empId);

    /**
     * 插入员工信息
     * @param employee 员工信息
     * @return 结果
     */
    int insertEmployee(Employee employee);

    /**
     * 修改员工信息
     * @param employee 员工信息
     * @return 结果
     */
    int updateEmployee(Employee employee);

    /**
     * 修改与员工头像
     * @param userName 员工用户名
     * @param avatar 头像地址
     * @return 结果
     */
    int updateEmployeeAvatar(String userName, String avatar);

    /**
     * 修改员工账号状态
     * @param userName 员工用户名
     * @param status 状态
     * @return 结果
     */
    int updateEmployeeStatus(String userName, String status);

    /**
     * 查询带有前缀的最大工号
     * @param prefix 工号前缀
     * @return 返回最大工号
     */
    String selectMaxJobNumberWithPrefix(String prefix);

    /**
     * 检查用户名（工号）是不是唯一
     * @param username 用户名（工号）
     * @return 结果
     */
    Employee checkUserNameUnique(String username);

    /**
     * 检查员工邮箱是不是唯一
     * @param email 邮箱
     * @return 结果
     */
    int checkEmailUnique(String email);

    /**
     * 检查员工手机号是不是唯一
     * @param phoneNumber 手机号
     * @return 结果
     */
    int checkPhoneNumberUnique(String phoneNumber);

    /**
     * 添加员工角色
     * @return 结果
     */
    int addEmployeeRole(EmployeeRole employeeRole);

    /**
     * 更新员工角色
     * @param employeeRole 员工角色
     * @return 结果
     */
    int updateEmployeeRole(EmployeeRole employeeRole);
}
