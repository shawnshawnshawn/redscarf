package com.baiye.redscarf.web.service;

import com.baiye.redscarf.common.result.PageResult;
import com.baiye.redscarf.web.common.form.SiegeListForm;
import com.baiye.redscarf.web.common.vo.SiegeListVo;

/**
 * @author baiye
 * @date 2021/3/15 2:38 下午
 **/
public interface FeigeSiegeService {

    PageResult<SiegeListVo> listSiegePage(String siegeType, SiegeListForm form);
}
