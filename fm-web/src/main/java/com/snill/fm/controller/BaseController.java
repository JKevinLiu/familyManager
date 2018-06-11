package com.snill.fm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.JsonResult;
import com.snill.fm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snill.fm.bean.User;
@RestController
public class BaseController {
    @Reference
    private UserService userService;

    @RequestMapping(value = "user/userinfo")
    public ResponseEntity<JsonResult> getNavigationList(){
        JsonResult r = new JsonResult();

        String status = "success";
        String desc = "成功";

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        User curUser = userService.findUserByUsername(username);

        r.setResult(curUser);

        return ResponseEntity.ok(r);
    }
}
