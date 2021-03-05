package com.baiye.redscarf.siege.service.rpc;

import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.common.form.SiegeListForm;
import com.baiye.redscarf.siege.common.vo.SiegeListVo;

/**
 * @author baiye
 * @date 2021/3/4 5:07 下午
 **/
public interface ISiegeService {
    PageVo<SiegeListVo> listSiegePage(String siegeType, SiegeListForm form);
}
