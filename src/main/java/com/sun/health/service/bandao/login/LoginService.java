package com.sun.health.service.bandao.login;

import com.sun.health.dto.bandao.wx.SessionDto;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.token.TokenService;
import com.sun.health.service.bandao.user.UserService;
import com.sun.health.service.bandao.wx.WxErrorCode;
import com.sun.health.service.bandao.wx.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService extends AbstractService {

    @Autowired
    private WxService wxService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @param code code
     * @return token
     */
    @Nullable
    public String login(String code) {

        SessionDto result = wxService.getUserSession(code);

        if (Objects.nonNull(result) && WxErrorCode.isSuccessful(result.getErrcode())) {

            UserEntity user = userService.getByOpenId(result.getOpenid());
            if (Objects.isNull(user)) {
                user = new UserEntity();
                user.setOpenId(result.getOpenid());
                user = userService.createUser(user);
            }
            return tokenService.createToken(user);
        }
        return null;
    }


}
