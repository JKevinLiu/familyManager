package com.snill.fm.controller;

import com.snill.fm.bean.base.User;
import com.snill.fm.bean.other.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/dologin")
    @ResponseBody
    public ResponseEntity<JsonResult> dologin(String username, String password){

        JsonResult r = new JsonResult();

        String status = "success";
        String desc = "成功";

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

        try {
            System.out.println("对用户[" + username + "]进行登录验证..验证开始");
            subject.login(usernamePasswordToken);
            System.out.println("对用户[" + username + "]进行登录验证..验证通过");

        }catch(UnknownAccountException uae){
            status = "fail";
            desc = "未知账户";
        }catch(IncorrectCredentialsException ice){
            status = "fail";
            desc = "密码不正确";
        }catch(LockedAccountException lae){
            status = "fail";
            desc = "账户已锁定";
        }catch(ExcessiveAttemptsException eae){
            status = "fail";
            desc = "用户名或密码错误次数过多";
        }catch(AuthenticationException ae){
            status = "fail";
            desc = "用户名或密码不正确";
        }finally {
            r.setStatus(status);
            r.setDesc(desc);
        }

        //验证是否登录成功
        if(subject.isAuthenticated()){
            // do something ?
        }else{
            usernamePasswordToken.clear();
        }

        return ResponseEntity.ok(r);

    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error";
    }
}
