package com.snill.fm.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.base.Permission;
import com.snill.fm.bean.base.Role;
import com.snill.fm.bean.base.User;
import com.snill.fm.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm extends AuthorizingRealm {
    //用于用户查询
    @Reference
    private UserService userService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String username= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = userService.findUserByUsername(username);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Role role = user.getRole();

        //添加角色
        simpleAuthorizationInfo.addRole(role.getCode());
        for (Permission permission:role.getPermissionList()) {
            //添加权限
            simpleAuthorizationInfo.addStringPermission(permission.getCode());
        }

        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
