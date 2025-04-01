package cn.bms.common.core.domain.model;

import lombok.Data;

@Data
public class LoginBody {
    private String username; // 用户名
    private String password;  // 密码
    private String code;   // 验证码
    private String uuid;   // 唯一标识符
}
