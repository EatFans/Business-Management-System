package cn.bms.web.controller.system;

import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.SystemMenu;
import cn.bms.domain.model.LoginUser;
import cn.bms.framework.web.service.TokenService;
import cn.bms.system.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统菜单控制器
 *
 * @author Fan
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    @Autowired
    private TokenService tokenService;

    /**
     * 用户获取系统菜单接口
     * @return 响应结果
     */
    @GetMapping("/get")
    public ApiResponse getMenu(HttpServletRequest request){
        ApiResponse response = ApiResponse.success();
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser == null)
            return ApiResponse.error("登录用户不存在");
        Long empId = loginUser.getEmpId();
        List<SystemMenu> menus = systemMenuService.getMenusByEmpId(empId);
        response.put("menus",menus);
        return response;
    }

}
