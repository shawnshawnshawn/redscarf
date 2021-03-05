package com.baiye.redscarf.siege.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author baiye
 * @date 2021/3/4 6:18 下午
 **/
@Data
public class UserAccountDto {

    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 登录密码
     */
    private String password;

    /**
     * (0:未登录;1:已登录)
     */
    private Integer loginStatus;

    /**
     * 上次登录时间
     */
    private Date loginTime;

    private Date updateTime;

    private Date createTime;
}
