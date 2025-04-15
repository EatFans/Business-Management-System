package cn.bms.system.mapper;

import cn.bms.domain.entity.Employee;
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
    public Employee selectEmployeeByUserName(String userName);

    /**
     * 通过员工主键id查询用户
     * @param empId 员工id
     * @return 员工对象信息
     */
    public Employee selectEmployeeById(Long empId);

    /**
     * 插入员工信息
     * @param employee 员工信息
     * @return 结果
     */
    public int insertEmployee(Employee employee);

    /**
     * 修改员工信息
     * @param employee 员工信息
     * @return 结果
     */
    public int updateEmployee(Employee employee);

    /**
     * 修改与员工头像
     * @param userName 员工用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateEmployeeAvatar(String userName, String avatar);

    /**
     * 修改员工账号状态
     * @param userName 员工用户名
     * @param status 状态
     * @return 结果
     */
    public int updateEmployeeStatus(String userName, String status);
}
