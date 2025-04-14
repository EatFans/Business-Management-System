package cn.bms.web.controller.common;

import cn.bms.common.constant.CacheConstants;
import cn.bms.common.utils.uuid.UuidUtil;
import cn.bms.domain.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;


/**
 * 图片验证码控制类
 *
 * @author Fan
 */
@RestController
public class CaptchaController {
    private final String IMAGE_CODE_TYPE = "math";  // 后续将其改成可从配置文件中修改

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 获取图片验证码
     */
    @GetMapping("/captchaCode")
    public ApiResponse getImageCode() throws IOException {
        ApiResponse response = ApiResponse.success();
        // 存储验证码信息
        String uuid = UuidUtil.easyUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        capStr = code = captchaProducer.createText();
        image = captchaProducer.createImage(capStr);

        // TODO: redis 将图片验证码信息存入缓存
        // 转换流
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image,"jpg",baos);
            byte[] imageBytes = baos.toByteArray();
            response.put("img", Base64.getEncoder().encodeToString(imageBytes));
            response.put("uuid",uuid);
        } catch (IOException e){
            return ApiResponse.error(e.getMessage());
        }
        return response;

    }

}
