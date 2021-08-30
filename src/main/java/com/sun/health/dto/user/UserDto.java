package com.sun.health.dto.user;

import com.sun.health.dto.BaseDTO;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/30
 **/
public class UserDto extends BaseDTO {

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
