package com.baiye.redscarf.siege.service.rpc.impl;

import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.dao.entity.SiegeDataEntity;
import com.baiye.redscarf.siege.dao.entity.SiegePostsEntity;
import com.baiye.redscarf.siege.service.biz.SiegeDataService;
import com.baiye.redscarf.siege.service.biz.SiegePostsService;
import com.baiye.redscarf.siege.service.reference.service.FeignUserService;
import com.baiye.redscarf.siege.service.rpc.ISiegeService;
import com.baiye.redscarf.siege.common.dto.UserAccountDto;
import com.baiye.redscarf.siege.common.enums.SiegeTypeEnum;
import com.baiye.redscarf.siege.common.form.SiegeListForm;
import com.baiye.redscarf.siege.common.obj.PageQuerySiegeConditions;
import com.baiye.redscarf.siege.common.vo.SiegeListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiye
 * @date 2021/3/4 5:07 下午
 **/
@Service
public class ISiegeServiceImpl implements ISiegeService {

    private static final Logger log = LoggerFactory.getLogger(ISiegeServiceImpl.class);

    @Resource
    private SiegePostsService siegePostsService;

    @Resource
    private SiegeDataService siegeDataService;

    @Resource
    private FeignUserService feignUserService;

    @Override
    public PageVo<SiegeListVo> listSiegePage(String siegeType, SiegeListForm form) {
        PageQuerySiegeConditions obj = new PageQuerySiegeConditions();
        obj.setPageSize(form.getPageSize());
        obj.setSiegeType(SiegeTypeEnum.getSiegeTypeByMsg(siegeType).getCode());
        obj.setStartPage(form.startPage());
        obj.setSearchKey(form.getSearchKey());
        PageVo<SiegePostsEntity> pageVo = siegePostsService.pageQuerySiegeByConditions(obj);
        List<SiegePostsEntity> dtos = pageVo.getDtos();
        PageVo<SiegeListVo> siegeListVoPageVo = new PageVo<>();
        siegeListVoPageVo.setCount(pageVo.getCount());
        if (!CollectionUtils.isEmpty(dtos)) {
            List<SiegeListVo> vos = new ArrayList<>(dtos.size());
            for (SiegePostsEntity dto : dtos) {
                SiegeListVo vo = new SiegeListVo();
                UserAccountDto user = feignUserService.getUserAccountInfo(dto.getUserId());
                vo.setNickName(user.getNickName());
                vo.setSiegeTitle(dto.getSiegeTitle());
                vo.setImages(dto.getSiegeImages());
                SiegeDataEntity siegeDataEntity = siegeDataService.getSiegeDataById(dto.getId());
                vo.setUpNum(siegeDataEntity.getUpNum());
                vos.add(vo);
            }
            siegeListVoPageVo.setDtos(vos);
        }
        return siegeListVoPageVo;
    }
}
