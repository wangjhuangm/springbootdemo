package com.wangj.dao;

import com.wangj.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getOne(int id);

    List<User> getAll();

    int insert(User user);

    void update(User user);

    void delete(int id);

    void multiInsert(List<User> users);

    List<User> getAll(String name);
}
