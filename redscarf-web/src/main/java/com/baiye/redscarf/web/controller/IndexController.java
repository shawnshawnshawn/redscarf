package com.baiye.redscarf.web.controller;

import com.baiye.redscarf.common.result.PageResult;
import com.baiye.redscarf.web.common.form.SiegeListForm;
import com.baiye.redscarf.web.common.vo.SiegeListVo;
import com.baiye.redscarf.web.service.FeigeSiegeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/15 2:26 下午
 **/
@Controller
@RequestMapping("/home")
public class IndexController {

    @Resource
    private FeigeSiegeService feigeSiegeService;

    @RequestMapping(value = "/{siegeType}", method = RequestMethod.GET)
    public String index(ModelAndView model, @PathVariable("siegeType") String siegeType) {
        SiegeListForm form = new SiegeListForm();
        PageResult<SiegeListVo> siegeListVoPageResult = feigeSiegeService.listSiegePage(siegeType, form);
        model.addObject("siegeList", siegeListVoPageResult.getData().getDtos());
        return "index";
    }
}
