package cn.bms.framework.web.service;

import cn.bms.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * token 业务处理
 *
 * @author Fan
 */
@Component
public class TokenService {

    @Value("token.value")
    private String header;

    @Value("token.secret")
    private String secret;

    @Value("token.expireTime")
    private int expireTime;
    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TWENTY = 20 * 60 * 1000L;

    private final RedisCache redisCache;

    @Autowired
    public TokenService(RedisCache redisCache){
        this.redisCache = redisCache;
    }

    // TODO: TokenService待完成
}
