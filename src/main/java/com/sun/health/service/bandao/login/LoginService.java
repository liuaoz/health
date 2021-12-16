package com.sun.health.service.bandao.login;

import com.sun.health.dto.bandao.wx.SessionDto;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.token.TokenService;
import com.sun.health.service.bandao.wx.WxErrorCode;
import com.sun.health.service.bandao.wx.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService extends AbstractService {

    @Autowired
    private WxService wxService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取token
     *
     * @param code code
     */
    public void login(String code) {

        tokenService.generateToken(null);

        SessionDto result = wxService.login(code);

        if (Objects.nonNull(result) && WxErrorCode.isSuccessful(result.getErrcode())) {

            //todo
        } else {

        }

    }


}
