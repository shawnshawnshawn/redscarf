package com.baiye.redscarf.user.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserSiegeEntity {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 围城帖子id
     */
    private Long siegeId;

    /**
     * 是否点赞（0：否；1：是；2：踩）
     */
    private Integer isUp;

    /**
     * 点赞时间
     */
    private Date upTime;

    /**
     * 是否收藏（0：否；1：是）
     */
    private Integer isCollect;

    /**
     * 收藏时间
     */
    private Date collectTime;

    /**
     * 是否转发（0：否；1：是）
     */
    private Integer isForward;

    /**
     * 转发时间
     */
    private Date forwardTime;

    private Date updateTime;

    private Date createTime;
}