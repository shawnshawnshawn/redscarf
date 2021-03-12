package com.baiye.redscarf.siege.rpc;

import com.baiye.redscarf.common.param.BaseForm;
import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.form.SiegeListForm;
import com.baiye.redscarf.siege.vo.GetSiegeVo;
import com.baiye.redscarf.siege.vo.SiegeListVo;

/**
 * @author baiye
 * @date 2021/3/4 5:07 下午
 **/
public interface ISiegeService {
    PageVo<SiegeListVo> listSiegePage(String siegeType, SiegeListForm form);

    GetSiegeVo getSiege(Long id, BaseForm form);
}
