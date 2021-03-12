package com.baiye.redscarf.siege.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SiegeDataEntity {
    private Long id;

    /**
     * 围城帖子id
     */
    private Long siegeId;

    /**
     * 点赞数量
     */
    private Integer upNum;

    /**
     * 踩数量
     */
    private Integer downNum;

    /**
     * 收藏数量
     */
    private Integer collectNum;

    /**
     * 转发数量
     */
    private Integer forwardNum;

    private Date updateTime;

    private Date createTime;
}