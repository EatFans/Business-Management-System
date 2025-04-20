package cn.bms.framework.web.service;

import cn.bms.common.constant.CacheConstants;
import cn.bms.common.constant.Constants;
import cn.bms.common.core.redis.RedisCache;
import cn.bms.common.utils.StringUtils;
import cn.bms.common.utils.uuid.UuidUtil;
import cn.bms.domain.model.LoginUser;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token 业务处理
 *
 // TODO: TokenService待完成
 *
 * @author Fan
 */
@Component
public class TokenService {
    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;
    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TWENTY = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;


    /**
     * 创建登录令牌
     * @param loginUser 登录信息
     * @return 登录令牌
     */
    public String createLoginToken(LoginUser loginUser){
        String loginUserKey = UuidUtil.fastUUID();
        loginUser.setLoginUserKey(loginUserKey);

        refreshToken(loginUser);    // 把这个loginUser对象存入Redis中，每次做验证时候只需要从缓存中找，不需要从数据库里查

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, loginUserKey);
        claims.put(Constants.JWT_USERNAME, loginUser.getUsername());

        return createLoginToken(claims);
    }


    /**
     * 从已有的数据去生成登录令牌
     * @param claims 数据
     * @return 登录令牌
     */
    private String createLoginToken(Map<String, Object> claims){
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
        return token;
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser){
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser登录信息存储到缓存中
        String loginUserKey = getTokenKey(loginUser.getLoginUserKey());
        redisCache.setCacheObject(loginUserKey,loginUser,expireTime, TimeUnit.MINUTES);
    }

    /**
     * 获取登录用户
     * @param request 请求信息
     * @return 返回登录用户
     */
    public LoginUser getLoginUser(HttpServletRequest request){
        String token = getToken(request);
        // 检查token令牌是否为空
        if (StringUtils.isNotEmpty(token)){
            try {
                Claims claims = parseToken(token);
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String tokenKey = getTokenKey(uuid);
                JSONObject jsonObject = (JSONObject) redisCache.getCacheObject(tokenKey);

                LoginUser loginUser = jsonObject.toJavaObject(LoginUser.class);

                if (loginUser != null && loginUser.getEmployee() == null) {
                    log.warn("Employee is null for LoginUser with empId: {}", loginUser.getEmpId());
                }

                return loginUser;

            } catch (Exception e){
                // TODO： 将异常记录到日志文件中
//                log.info("获取用户信息异常'{}'", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 获取请求token
     *
     * @param request 请求信息
     * @return 返回token
     */
    private String getToken(HttpServletRequest request){
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)){
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 解析token
     * @param token token令牌
     * @return 返回解析后的数据
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取token令牌token
     * @param uuid 唯一标识符
     * @return 返回token Key
     */
    private String getTokenKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 验证登录token
     * @param loginUser 登录信息
     */
    public void verifyToken(LoginUser loginUser){
        Long expireTime = loginUser.getExpireTime();
        Long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TWENTY){
            refreshToken(loginUser);
        }
    }
}
