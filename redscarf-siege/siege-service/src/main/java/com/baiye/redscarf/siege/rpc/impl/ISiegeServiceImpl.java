package com.baiye.redscarf.siege.rpc.impl;

import com.baiye.redscarf.common.param.BaseForm;
import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.reference.service.FeignUserService;
import com.baiye.redscarf.siege.vo.GetSiegeVo;
import com.baiye.redscarf.siege.elastic.SiegeElastic;
import com.baiye.redscarf.siege.entity.SiegeDataEntity;
import com.baiye.redscarf.siege.entity.SiegePostsEntity;
import com.baiye.redscarf.siege.biz.SiegeDataService;
import com.baiye.redscarf.siege.biz.SiegePostsService;
import com.baiye.redscarf.siege.elastic.SiegeElasticService;
import com.baiye.redscarf.siege.rpc.ISiegeService;
import com.baiye.redscarf.siege.dto.UserAccountDto;
import com.baiye.redscarf.siege.enums.SiegeTypeEnum;
import com.baiye.redscarf.siege.form.SiegeListForm;
import com.baiye.redscarf.siege.obj.PageQuerySiegeConditions;
import com.baiye.redscarf.siege.vo.SiegeListVo;
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

    @Resource
    private SiegeElasticService siegeElasticService;

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
                if (siegeDataEntity != null) {
                    vo.setUpNum(siegeDataEntity.getUpNum());
                }
                vos.add(vo);
            }
            siegeListVoPageVo.setDtos(vos);
        }
        return siegeListVoPageVo;
    }

    @Override
    public GetSiegeVo getSiege(Long id, BaseForm form) {
        SiegeElastic se = siegeElasticService.findById(id);
        log.info("siege -> {}", se);
        GetSiegeVo vo = new GetSiegeVo();
        vo.setSiegeTitle(se.getSiege_title());
        vo.setSiegeType(se.getSiege_type());
        vo.setUpNum(se.getUp_num());
        vo.setSiegeImages(se.getSiege_images());
        vo.setSiegeInfo(se.getSiege_info());
        vo.setCollectNum(se.getCollect_num());
        vo.setCreateTime(se.getCreate_time());
        vo.setDownNum(se.getDown_num());
        vo.setForwardNum(se.getForward_num());
        vo.setId(se.get_id());
        vo.setSiegeStatus(se.getSiege_status());
        vo.setUserId(se.getUser_id());
        return vo;
    }
}
