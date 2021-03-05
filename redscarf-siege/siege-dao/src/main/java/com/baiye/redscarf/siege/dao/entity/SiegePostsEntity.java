package com.baiye.redscarf.siege.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SiegePostsEntity {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 帖子标题
     */
    private String siegeTitle;

    /**
     * 帖子类型（1：东；2：南；3：西；4：北）
     */
    private Integer siegeType;

    /**
     * 帖子封面图
     */
    private String siegeImages;

    /**
     * 帖子信息
     */
    private String siegeInfo;

    /**
     * 围城帖子状态（0：正常；1：删除）
     */
    private Integer siegeStatus;

    private Date updateTime;

    private Date createTime;
}