package com.snill.fm.mapper;

import com.snill.fm.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    public Role getRoleById(Integer id);

    public List<Role> getRoleList();

    public int add(Role role);

    public int update(@Param("id") Integer id, @Param("role") Role role);

    public int delete(Integer id);

}
