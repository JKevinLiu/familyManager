package com.snill.fm.service;

import com.snill.fm.bean.Permission;

import java.util.List;

public interface PermissionService {
    public Permission getPermissionById(Integer id);

    public List<Permission> getPermissionList();

    public int add(Permission permission);

    public int update(Integer id, Permission permission);

    public int delete(Integer id);

    public Permission getPermissionByCode(String code);
}
