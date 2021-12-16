package com.sun.health.service.bandao.token;

import com.sun.health.core.util.JsonUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends AbstractService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String createToken(UserEntity user) {

        String token = StringUtil.rand32Str();

        redisTemplate.opsForValue().set(token, JsonUtil.toJson(user));

        return token;
    }

    @Nullable
    public UserEntity getUserByToken(String token) {

        if (isValidToken(token)) {
            String value = redisTemplate.opsForValue().get(token);
            return JsonUtil.fromJson(value, UserEntity.class);
        }
        return null;
    }

    public boolean isValidToken(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }
}
