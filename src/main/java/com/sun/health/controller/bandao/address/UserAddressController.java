package com.sun.health.controller.bandao.address;

import com.sun.health.controller.BaseController;
import com.sun.health.core.annotation.CurrentUser;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.address.UserAddressDto;
import com.sun.health.entity.bandao.address.UserAddressEntity;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.bandao.address.UserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class UserAddressController extends BaseController {

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 默认地址
     */
    @GetMapping("/default")
    public JsonRet<UserAddressDto> getDefaultAddress(@CurrentUser UserEntity user) {
//        UserAddressEntity addressEntity = userAddressService.getDefaultAddress(user.getId());
//        UserAddressDto dto = new UserAddressDto();
//        BeanUtils.copyProperties(addressEntity, dto);
//        return JsonRet.success(dto);
        List<UserAddressDto> list = list(user).getData();
        if (CollectionUtils.isEmpty(list)) {
            return JsonRet.success();
        }
        return JsonRet.success(list.get(0));
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public JsonRet<UserAddressDto> getDetail(@PathVariable Long id) {
        UserAddressEntity addressEntity = userAddressService.findById(id);
        UserAddressDto dto = new UserAddressDto();
        BeanUtils.copyProperties(addressEntity, dto);
        return JsonRet.success(dto);
    }

    /**
     * 保存
     */
    @PostMapping
    public JsonRet<Boolean> save(@RequestBody UserAddressDto addressDto, @CurrentUser UserEntity user) {
        addressDto.setUserId(user.getId());
        return JsonRet.success(userAddressService.save(addressDto));
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public JsonRet<Boolean> delete(@PathVariable Long id) {
        return JsonRet.success(userAddressService.delete(id));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public JsonRet<List<UserAddressDto>> list(@CurrentUser UserEntity user) {
        List<UserAddressEntity> userAddressEntities = userAddressService.getList(user.getId());
        List<UserAddressDto> addressDtoList = userAddressEntities.stream().map(t -> {
            UserAddressDto addressDto = new UserAddressDto();
            BeanUtils.copyProperties(t, addressDto);
            return addressDto;
        }).collect(Collectors.toList());
        return JsonRet.success(addressDtoList);
    }
}
