package cn.bms.system.service.impl;

import cn.bms.common.utils.StringUtil;
import cn.bms.domain.entity.Employee;
import cn.bms.system.mapper.EmployeeMapper;
import cn.bms.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 *
 * @author Fan
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 通过用户名来查找员工用户
     * @param username 用户名(工号）
     * @return 返回员工
     */
    @Override
    public Employee selectEmployeeByUserName(String username) {
        if (StringUtil.isNotEmpty(username)){
            return employeeMapper.selectEmployeeByUserName(username);
        }
        return null;
    }

    /**
     * 通过员工用户id来查找员工用户
     * @param id id
     * @return 返回员工
     */
    @Override
    public Employee selectEmployeeById(Long id){
        if (id != null && id != 0){
            return employeeMapper.selectEmployeeById(id);
        }
        return null;
    }

    /**
     * 添加员工用户
     * @param employee 员工
     * @return 如果添加成功就返回true，否则返回false
     */
    @Override
    public boolean addEmployee(Employee employee) {
        int flag = employeeMapper.insertEmployee(employee);
        return flag > 0;
    }

    /**
     * 更新员工用户
     * @param employee 员工
     * @return 如果更新成功就返回true，否则就返回false
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        int flag = employeeMapper.updateEmployee(employee);
        return flag > 0;
    }
}
