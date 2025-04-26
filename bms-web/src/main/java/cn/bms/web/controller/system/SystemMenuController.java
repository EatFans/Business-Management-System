package cn.bms.web.controller.system;

import cn.bms.domain.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统菜单控制器
 *
 * @author Fan
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {

    @GetMapping("/getList")
    public ApiResponse getMenuItemList(){
        ApiResponse response = ApiResponse.success();

        return response;
    }

}
