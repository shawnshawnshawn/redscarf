package com.baiye.redscarf.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baiye
 * @date 2021/3/1 4:46 下午
 **/
@Controller
public class TestController {

    @RequestMapping(value = "/hello")
    public String test(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("AAA", "test a aa");
        map.put("BBB", "test bbbb");
        model.addAttribute("maps", map);
        return "index";
    }
}
