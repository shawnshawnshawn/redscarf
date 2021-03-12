package com.baiye.redscarf.siege.biz;

import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.entity.SiegePostsEntity;
import com.baiye.redscarf.siege.obj.PageQuerySiegeConditions;

/**
 * @author baiye
 * @date 2021/3/4 5:06 下午
 **/
public interface SiegePostsService {
    PageVo<SiegePostsEntity> pageQuerySiegeByConditions(PageQuerySiegeConditions obj);
}
