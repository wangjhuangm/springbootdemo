package com.wangj.service.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

public interface SchedulerService {
    public void schedulerTest() throws InterruptedException;
    public void schedulerTask2() throws InterruptedException;
}
