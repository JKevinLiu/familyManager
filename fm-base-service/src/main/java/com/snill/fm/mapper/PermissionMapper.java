package com.snill.fm.mapper;

import com.snill.fm.bean.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    public Permission getPermissionById(Integer id);

    public List<Permission> getPermissionList();

    public int add(Permission permission);

    public int update(@Param("id") Integer id, @Param("Permission") Permission permission);

    public int delete(Integer id);
}
