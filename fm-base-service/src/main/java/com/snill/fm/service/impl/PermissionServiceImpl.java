package com.snill.fm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.bean.Permission;
import com.snill.fm.mapper.PermissionMapper;
import com.snill.fm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionMapper.getPermissionById(id);
    }

    @Override
    public List<Permission> getPermissionList() {
        return permissionMapper.getPermissionList();
    }

    @Override
    public int add(Permission permission) {
        return permissionMapper.add(permission);
    }

    @Override
    public int update(Integer id, Permission permission) {
        return permissionMapper.update(id, permission);
    }

    @Override
    public int delete(Integer id) {
        return permissionMapper.delete(id);
    }

    @Override
    public Permission getPermissionByCode(String code) {
        return permissionMapper.getPermissionByCode(code);
    }
}