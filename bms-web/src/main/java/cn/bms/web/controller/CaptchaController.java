package cn.bms.web.controller;

import cn.bms.common.constant.CacheConstants;
import cn.bms.common.core.domain.ApiResponse;
import cn.bms.common.utils.UuidUtil;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;


/**
 * 图片验证码控制类
 *
 * @author Fan
 */
@RestController
public class CaptchaController {
    private final String IMAGE_CODE_TYPE = "char";  // 后续将其改成可从配置文件中修改

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 获取图片验证码
     */
    @GetMapping("/captchaCode")
    public ApiResponse getImageCode(){
        ApiResponse response = ApiResponse.success();

        // 存储验证码信息
        String uuid = UuidUtil.easyUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(IMAGE_CODE_TYPE)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(IMAGE_CODE_TYPE)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        // TODO:

        return response;

    }
}
