package com.snill.fm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/userinfo")
    public String userinfo(){
        return "user/userinfo";
    }

    @RequestMapping(value = "/orderinfo")
    public String orderinfo(){
        return "order/orderinfo";
    }

    @RequestMapping(value = "/reportinfo")
    public String reportinfo(){
        return "report/reportinfo";
    }

    @RequestMapping(value = "/productinfo")
    public String productinfo(){
        return "product/productinfo";
    }

    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }

}
