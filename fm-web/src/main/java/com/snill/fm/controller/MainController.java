package com.snill.fm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.Permission;
import com.snill.fm.service.PermissionService;
import com.snill.fm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Reference
    private UserService userService;

    @Reference
    private PermissionService permissionService;

    @RequestMapping(value = "/nav/{code}")
    public String nav(@PathVariable("code") String code){
        String ret = "";

        try{
            SecurityUtils.getSubject().checkPermission(code);

            Permission permission = permissionService.getPermissionByCode(code);
            ret = permission.getUrl();

            //捕获无权限异常
        }catch (AuthorizationException ae){
            ret = "main";
            log.info(ae.getMessage() + ", 返回主页...");
        }

        return ret;
    }

    @RequestMapping(value = "/subnav/{option}")
    public String order(@PathVariable("option") String option){

        if(option != null){
            switch(option){
                case "add_order":
                    return "order/order_option";
            }
        }

        return "order";
    }

    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/page")
    public String page(){
        return "page/page";
    }
}
