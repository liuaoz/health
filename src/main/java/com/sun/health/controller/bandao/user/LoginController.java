package com.sun.health.controller.bandao.user;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.core.util.StringUtil;
import com.sun.health.dto.bandao.user.LoginDto;
import com.sun.health.service.bandao.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public JsonRet<String> login(@RequestBody LoginDto dto) {

        logger.info("login.code--->{}", dto.getCode());

        String token = loginService.login(dto.getCode());

        if (StringUtil.isEmpty(token)) {
            return JsonRet.fail();
        }

        return JsonRet.success(token);
    }
}
