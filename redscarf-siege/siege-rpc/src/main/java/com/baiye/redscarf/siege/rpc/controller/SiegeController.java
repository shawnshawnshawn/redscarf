package com.baiye.redscarf.siege.rpc.controller;

import com.baiye.redscarf.common.param.BaseForm;
import com.baiye.redscarf.common.result.PageResult;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.siege.common.vo.GetSiegeVo;
import com.baiye.redscarf.siege.service.rpc.ISiegeService;
import com.baiye.redscarf.siege.common.form.SiegeListForm;
import com.baiye.redscarf.siege.common.vo.SiegeListVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/4 5:31 下午
 **/
@RestController
@RequestMapping("/siege")
public class SiegeController {

    @Resource
    private ISiegeService iSiegeService;

    @RequestMapping(value = "/listSiegePage/{siegeType}", method = RequestMethod.GET)
    public PageResult<SiegeListVo> listSiegePage(@PathVariable("siegeType") String siegeType, SiegeListForm form) {
        return PageResult.toPageResult(iSiegeService.listSiegePage(siegeType, form));
    }

    @RequestMapping(value = "/getSiege/{id}", method = RequestMethod.GET)
    public Result<GetSiegeVo> getSiege(@PathVariable("id") Long id, BaseForm form) {
        return Result.toResult(iSiegeService.getSiege(id, form));
    }
}
