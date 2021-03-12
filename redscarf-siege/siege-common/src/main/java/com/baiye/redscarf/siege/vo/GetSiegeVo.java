package com.baiye.redscarf.siege.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author baiye
 * @date 2021/3/7 1:01 上午
 **/
@Data
public class GetSiegeVo {
    private Long id;

    private Long userId;

    private String siegeImages;

    private String createTime;

    private String siegeInfo;

    private String siegeTitle;

    private Integer siegeStatus;

    private Integer siegeType;

    private Integer collectNum;

    private Integer downNum;

    private Integer forwardNum;

    private Integer upNum;
}
