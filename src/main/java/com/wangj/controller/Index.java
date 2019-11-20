package com.wangj.controller;

import com.wangj.dao.UserMapper;
import com.wangj.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Index {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index() {
        User user = userMapper.getOne(1);
        return "hello,Spring boot demo! username: " + user.getName();
    }
}
