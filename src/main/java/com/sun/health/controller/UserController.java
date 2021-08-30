package com.sun.health.controller;

import com.sun.health.dto.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/28
 **/
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {


    @PostMapping("login")
    public ResponseEntity<String> login(UserDto userDto) {

        logger.info("login user: {}", userDto.getUserName());
        //todo

        return ResponseEntity.ok("success");
    }

}
