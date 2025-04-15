package cn.bms.framework.web.service;

import cn.bms.common.utils.StringUtil;
import cn.bms.domain.entity.Employee;
import cn.bms.domain.model.LoginUser;
import cn.bms.system.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 员工用户加载服务
 *
 * @author Fan
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据用户名加载用户信息的方法，用于 Spring Security 登录认证时调用。
     *
     * 这是 UserDetailsService 接口中的核心方法。
     * 当用户尝试登录时，Spring Security 会自动调用这个方法，
     * 并将用户在登录表单中输入的用户名作为参数传入。
     *
     * 在这个方法中，我们通常会从数据库中查询该用户名对应的用户信息，
     * 然后将查询到的信息封装成一个实现了 UserDetails 接口的对象（比如自定义的 LoginUser 类）返回。
     *
     * 如果用户名不存在，需要抛出 UsernameNotFoundException 异常，Spring Security 会捕获这个异常并返回提示“用户名不存在”。
     *
     * @param username 用户在登录表单中输入的用户名
     * @return UserDetails Spring Security 用于后续密码校验和权限验证的用户对象
     * @throws UsernameNotFoundException 如果用户名不存在，需要抛出该异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //TODO
        Employee user = employeeService.selectEmployeeByUserName(username);
        if (StringUtil.isNull(user)){
            log.info("登录用户：{} 不存在.", username);

        }
        //TODO 检查员工是否被删除

        //TODO 检查员工账号状态


        return new LoginUser();
    }

    private LoginUser createLoginUser(Employee employee){
        // TODO
        return new LoginUser();
    }
}
