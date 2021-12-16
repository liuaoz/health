package com.sun.health.service.bandao.token;

import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends AbstractService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public String generateToken(UserEntity user){

        redisTemplate.opsForValue().set("test","token22222222222");


        return null;
    }
}
