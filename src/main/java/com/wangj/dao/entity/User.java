package com.wangj.dao.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private int age;
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        User user = (User) obj;
        if (user.getId() == id) {
            return true;
        }
        return false;
    }
//
//    @Override
//    public int hashCode() {
//        return this.id;
//    }
}
