package com.sun.health.service.bandao.user;

import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.repository.bandao.user.UserRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService extends AbstractService {

    @Autowired
    private UserRepository userRepository;

    @Nullable
    public UserEntity getByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public boolean exist(String openId) {
        return !Objects.isNull(getByOpenId(openId));
    }

}
