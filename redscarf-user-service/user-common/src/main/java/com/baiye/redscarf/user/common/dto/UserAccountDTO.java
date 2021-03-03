package com.baiye.redscarf.user.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author baiye
 * @date 2021/3/3 10:33 上午
 **/
@Data
public class UserAccountDTO {

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
     * (0:未登录;1:已登录)
     */
    private Integer loginStatus;

    /**
     * 上次登录时间
     */
    private Date loginTime;

    private Date createTime;
}
