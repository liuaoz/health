package com.sun.health.service.bandao.address;

import com.sun.health.dto.bandao.address.UserAddressDto;
import com.sun.health.entity.bandao.address.UserAddressEntity;
import com.sun.health.repository.bandao.address.UserAddressRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserAddressService extends AbstractService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    /**
     * 默认收件地址
     */
    public UserAddressEntity getDefaultAddress(Long userId) {
        return userAddressRepository.findByUserIdAndIsDefault(userId, true);
    }

    /**
     * 详情
     */
    public UserAddressEntity findById(Long id) {
        return userAddressRepository.getById(id);
    }

    /**
     * 保存地址，新增或修改
     */
    public boolean save(UserAddressDto addressDto) {
        UserAddressEntity userAddressEntity;
        if (Objects.isNull(addressDto.getId())) {
            userAddressEntity = new UserAddressEntity();
        } else {
            userAddressEntity = userAddressRepository.getById(addressDto.getId());
        }
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
