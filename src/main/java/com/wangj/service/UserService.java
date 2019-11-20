package com.wangj.service;

import com.wangj.dao.entity.User;

import java.util.List;

public interface UserService {

    User getOne(int id);

    List<User> getAll();

    void insert(User user);

    void update(User user);

    User delete(int id);
}
