package com.snill.fm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.bean.base.User;
import com.snill.fm.mapper.UserMapper;
import com.snill.fm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }


    @Override
    public int update(Integer id, User user) {
        return userMapper.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}