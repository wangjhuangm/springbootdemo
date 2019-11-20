package com.wangj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public RedisTemplate<Object, Object> getInstance(){
        return redisTemplate;
    }

    public void set(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(Object key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : value.toString();
    }

    public void set(Object key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    public void delete(Object key) {
        redisTemplate.delete(key);
    }

    public void setForList(Object key, Object value, long index) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public String getForList(Object key, long index) {
        Object value = redisTemplate.opsForList().index(key, index);
        return value == null ? null : value.toString();
    }

    public List<?> rangeForList(Object key, long start, long end) {
        List<?> list = redisTemplate.opsForList().range(key, start, end);
        return list == null || list.size() == 0 ? null : list;
    }

    public void trimForList(Object key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    public long sizeForList(Object key) {
        return redisTemplate.opsForList().size(key);
    }

    public long leftPushForList(Object key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public long leftPushForList(Object key, List<?> list) {
        return redisTemplate.opsForList().leftPushAll(key, list);
    }

    public long rightPushForList(Object key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Object leftPopForList(Object key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public Object rightPopForList(Object key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public long removeForList(Object key, Object value, long count) {
        return redisTemplate.opsForList().remove(key, count, value);
    }


}
