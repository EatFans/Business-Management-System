package cn.bms.framework.web.service;

import cn.bms.common.constant.CacheConstants;
import cn.bms.common.constant.Constants;
import cn.bms.common.core.domain.model.LoginUser;
import cn.bms.common.core.redis.RedisCache;
import cn.bms.common.utils.IdUtils;
import cn.bms.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;
    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TWENTY = 20 * 60 * 1000L;

    private final RedisCache redisCache;

    @Autowired
    public TokenService(RedisCache redisCache){
        this.redisCache = redisCache;
    }


    /**
     * 获取登录用户
     * @param request 请求信息
     * @return 返回登录用户
     */
    public LoginUser getLoginUser(HttpServletRequest request){
        String token = getToken(request);
        // 检查token令牌是否为空
        if (StringUtil.isEmpty(token)){
            try {
                Claims claims = parseToken(token);
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String tokenKey = getTokenKey(uuid);
                LoginUser user = redisCache.getCacheObject(tokenKey);

                return user;

            } catch (Exception e){
                // TODO： 将异常记录到日志文件中
                e.printStackTrace();
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
        String token = request.getHeader(this.header);
        if (StringUtil.isEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)){
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
     * 创建Token
     * @param loginUser 登录用户
     * @return 返回创建完成的token
     */
    public String createToken(LoginUser loginUser){
//        String token = IdUtils.fastUUID();
//        loginUser.setToken(token);
//        setUserAgent(loginUser);
//        refreshToken(loginUser);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(Constants.LOGIN_USER_KEY, token);
//        claims.put(Constants.JWT_USERNAME, loginUser.getUsername());
//        return createToken(claims);

        return "";
    }

    public void setUserAgent(LoginUser loginUser){
        // TODO
//        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//        String ip = IpUtils.getIpAddr();
//        loginUser.setIpaddr(ip);
//        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
//        loginUser.setBrowser(userAgent.getBrowser().getName());
//        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    public void refreshToken(LoginUser loginUser){
//        loginUser.setLoginTime(System.currentTimeMillis());
//        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
//        // 根据uuid将loginUser缓存
//        String userKey = getTokenKey(loginUser.getToken());
//        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    public String createToken(Map<String,Object> claims) {
        // TODO:
        return " ";
    }
}
