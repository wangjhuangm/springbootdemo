package com.wangj.service.threadPool;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public interface ThreadPoolService {
    public void threadPoolTask();

    public void schedulerThreadPoolTask();

    public void cacheThreadPoolTask();

}
