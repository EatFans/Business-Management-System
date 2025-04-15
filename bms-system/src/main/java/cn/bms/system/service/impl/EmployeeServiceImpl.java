package cn.bms.system.service.impl;

import cn.bms.domain.entity.Employee;
import cn.bms.system.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 *
 * @author Fan
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * 通过用户名来查找员工用户
     * @param username 用户名
     * @return 返回员工
     */
    @Override
    public Employee selectEmployeeByUserName(String username) {
        // TODO : 去使用EmployeeMapper去查找

        return null;
    }
}
