package com.baiye.redscarf.siege.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author baiye
 * @date 2021/3/4 5:45 下午
 **/
@Data
public class SiegeListVo {

    private String icon;

    private String nickName;

    private String siegeTitle;

    private String images;

    private String hotComment;

    private Integer upNum;

    private Long siegeId;

    private Long userId;

    private String createTime;
}
