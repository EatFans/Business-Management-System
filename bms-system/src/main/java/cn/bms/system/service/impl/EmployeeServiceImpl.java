package cn.bms.system.service.impl;

import cn.bms.common.constant.EmployeeConstants;
import cn.bms.common.utils.StringUtil;
import cn.bms.domain.entity.Employee;
import cn.bms.system.mapper.EmployeeMapper;
import cn.bms.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    /**
     * 生成工号
     * @return 返回生成好的工号
     */
    @Override
    public String generateJobNumber() {
        String datePrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String maxJobNumber = employeeMapper.selectMaxJobNumberWithPrefix(datePrefix);

        int nextNum = 1;
        if (maxJobNumber != null && maxJobNumber.length() > 8){
            String suffix = maxJobNumber.substring(8);
            nextNum = Integer.parseInt(suffix) + 1;
        }
        return datePrefix + String.format("%03d",nextNum);

    }

    /**
     * 检查工号（用户名）是否唯一
     * @param employee 员工
     * @return 如果唯一就返回true，否则就返回false
     */
    @Override
    public boolean checkJobNumberUnique(Employee employee) {
        Long empId = StringUtil.isNull(employee.getEmpId()) ? -1L : employee.getEmpId();
        Employee info = employeeMapper.checkUserNameUnique(employee.getJobNumber());
        if (StringUtil.isNotNull(info) && info.getEmpId().longValue() != empId.longValue()){
            return EmployeeConstants.NOT_UNIQUE;
        }
        return EmployeeConstants.UNIQUE;
    }

    /**
     * 检查员工邮箱是否唯一
     * @param employee 员工
     * @return 如果唯一就返回true，否则就返回false
     */
    @Override
    public boolean checkEmailUnique(Employee employee) {
        int count = employeeMapper.checkEmailUnique(employee.getEmail());
        return count > 0 ? EmployeeConstants.NOT_UNIQUE : EmployeeConstants.UNIQUE;
    }

    /**
     * 检查员工手机号码是否唯一
     * @param employee 员工
     * @return 如果唯一就返回true，否则就返回false
     */
    @Override
    public boolean checkPhoneNumberUnique(Employee employee) {
        int count = employeeMapper.checkPhoneNumberUnique(employee.getPhoneNUmber());
        return count > 0 ? EmployeeConstants.NOT_UNIQUE : EmployeeConstants.UNIQUE;
    }
}
