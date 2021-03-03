package com.baiye.redscarf.user.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserAccountEntity {
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