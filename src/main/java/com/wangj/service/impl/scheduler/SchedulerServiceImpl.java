package com.wangj.service.impl.scheduler;

import com.wangj.service.scheduler.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    private final static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Async
    @Scheduled(cron = "0 * * * * ?")
    @Override
    public void schedulerTest() throws InterruptedException {
        logger.info("go into SchedulerService task1..................");
        Thread.sleep(10 * 1000);
        logger.info("go out SchedulerService task1..................");
    }

    @Async
    @Scheduled(cron = "0 * * * * ?")
    @Override
    public void schedulerTask2() throws InterruptedException {
        logger.info("go into SchedulerService task2..................");
        Thread.sleep(10 * 1000);
        logger.info("go out SchedulerService task2..................");
    }

}
