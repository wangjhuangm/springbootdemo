package com.wangj.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.wangj.dao.UserMapper;
import com.wangj.dao.entity.User;
import com.wangj.service.user.UserService;
import com.wangj.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String REDIS_PREFIX = "user";

    @Override
    public User getOne(int id) {
        User user = null;
        String value = redisUtil.get(REDIS_PREFIX + String.valueOf(id));
        if(value == null){
            user = userMapper.getOne(id);
            if (user != null) {
                logger.info("put user into redis");
                redisUtil.set(REDIS_PREFIX + String.valueOf(id), JSONObject.toJSONString(user), 10, TimeUnit.MINUTES);
            }
        }else {
            logger.info("get user from redis: " + value);
            user = JSONObject.parseObject(value, User.class);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        List<?> value = redisUtil.rangeForList(REDIS_PREFIX +"ALL", 0, -1);
        if(value == null){
            users = userMapper.getAll();
            if (users!=null && users.size()>0) {
                logger.info("put users into redis, " + JSONObject.toJSONString(users));
                redisUtil.rightPushForList(REDIS_PREFIX + "ALL", users);
            }
        }else {
            logger.info("get users from redis: " + value);
            users = (List<User>) value;
        }
        return users;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void insert(User user) {
        int id = userMapper.insert(user);
        logger.info("insert user to database, id: " + id + "; user.id = " + user.getId());
        redisUtil.set(REDIS_PREFIX + String.valueOf(id), JSONObject.toJSONString(user));
        redisUtil.rightPushForList(REDIS_PREFIX +"ALL", user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void update(User user) {
        User old = this.getOne(user.getId());
        logger.info(JSONObject.toJSONString(old));
        userMapper.update(user);
        redisUtil.set(REDIS_PREFIX + String.valueOf(user.getId()), JSONObject.toJSONString(user), 10, TimeUnit.MINUTES);
        redisUtil.removeForList(REDIS_PREFIX + "ALL", old, 0);
        redisUtil.rightPushForList(REDIS_PREFIX + "ALL", user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public User delete(int id) {
        User user = userMapper.getOne(id);
        userMapper.delete(id);
        redisUtil.delete(REDIS_PREFIX + String.valueOf(id));
        redisUtil.removeForList(REDIS_PREFIX + "ALL", user, 0);
        return user;
    }

    @Override
    public void multiInsert(List<User> users) {
        userMapper.multiInsert(users);
        logger.info("multi insert success");
    }
}
