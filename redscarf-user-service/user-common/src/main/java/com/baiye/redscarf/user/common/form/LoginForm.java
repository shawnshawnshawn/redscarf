package com.baiye.redscarf.user.common.form;

import lombok.Data;

/**
 * @author baiye
 * @date 2021/3/4 3:49 下午
 **/
@Data
public class LoginForm {

    private String phoneNo;

    private String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
