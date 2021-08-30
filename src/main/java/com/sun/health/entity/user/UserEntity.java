package com.sun.health.entity.user;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/30
 **/
@Entity
@Table(name = "tb_user")
public class UserEntity extends BaseEntity {

    private String userName;

    private String password;

    private String tel;

    private String email;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
