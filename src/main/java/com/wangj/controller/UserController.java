package com.wangj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangj.dao.entity.User;
import com.wangj.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String getOne(@PathVariable int id) {
        User user = userService.getOne(id);
        return JSONObject.toJSONString(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll() {
        List<User> users = userService.getAll();
        return JSONObject.toJSONString(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String update(@PathVariable int id, @RequestBody JSONObject userInfo) {
        User user = JSONObject.parseObject(userInfo.toJSONString(), User.class);
        user.setId(id);
        userService.update(user);
        return JSONObject.toJSONString(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insert(@RequestBody JSONObject userInfo) {
        User user = JSONObject.parseObject(userInfo.toJSONString(), User.class);
        userService.insert(user);
        return JSONObject.toJSONString(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        User user = userService.delete(id);
        return "delete successful! " + JSONObject.toJSONString(user);
    }

    @RequestMapping(value = "/multi", method = RequestMethod.POST)
    public String multiInsert(@RequestBody List<User> users) {
        userService.multiInsert(users);
        return "multi insert successful! " + JSONObject.toJSONString(users);
    }
}
