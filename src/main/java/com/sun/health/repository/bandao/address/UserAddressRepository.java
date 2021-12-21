package com.sun.health.repository.bandao.address;

import com.sun.health.entity.bandao.address.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity,Long> {

    List<UserAddressEntity> findByUserId(Long userId);
}
