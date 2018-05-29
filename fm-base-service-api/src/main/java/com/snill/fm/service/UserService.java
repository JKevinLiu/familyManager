package com.snill.fm.service;

import com.snill.fm.bean.base.User;

import java.util.List;

public interface UserService {
    public User getUserById(Integer id);
    public List<User> getUserList();
    public int add(User user);
    public int update(Integer id, User user);
    public int delete(Integer id);

    public User findUserByUsername(String username);
}
