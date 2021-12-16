package com.sun.health.repository.bandao.user;

import com.sun.health.entity.bandao.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByOpenId(String openId);
}
