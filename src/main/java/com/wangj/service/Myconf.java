package com.wangj.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:myapp.yml", ignoreResourceNotFound = true)
//@ConfigurationProperties(prefix = "threadpool")
public class Myconf {
    @Value("${threadpool.core-size}")
    private Integer coreSize;
    @Value("${threadpool.max-size}")
    private Integer maxSize;

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getCoreSize() {
        return coreSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }
}
