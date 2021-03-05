package com.baiye.redscarf.siege.service.biz;

import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.dao.entity.SiegePostsEntity;
import com.baiye.redscarf.siege.common.obj.PageQuerySiegeConditions;

/**
 * @author baiye
 * @date 2021/3/4 5:06 下午
 **/
public interface SiegePostsService {
    PageVo<SiegePostsEntity> pageQuerySiegeByConditions(PageQuerySiegeConditions obj);
}
