package com.sun.health.service.bandao.address;

import com.sun.health.dto.bandao.address.UserAddressDto;
import com.sun.health.entity.bandao.address.UserAddressEntity;
import com.sun.health.repository.bandao.address.UserAddressRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService extends AbstractService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    /**
     * 保存地址，新增或修改
     */
    public boolean save(UserAddressDto addressDto) {
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        BeanUtils.copyProperties(addressDto, userAddressEntity);
        userAddressRepository.save(userAddressEntity);
        return true;
    }

    /**
     * 地址列表
     */
    public List<UserAddressEntity> getList(Long userId) {
        return userAddressRepository.findByUserId(userId);
    }

    /**
     * 删除地址
     */
    public boolean delete(Long id) {
        userAddressRepository.deleteById(id);
        return true;
    }
}
