package com.baiye.redscarf.siege.common.obj;

import lombok.Data;

/**
 * @author baiye
 * @date 2021/3/4 6:01 下午
 **/
@Data
public class PageQuerySiegeConditions {

    private String searchKey;

    private Integer siegeType;

    private Integer startPage;

    private Integer pageSize;
}
