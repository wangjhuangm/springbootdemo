package com.wangj.service.impl.threadPool;

import com.wangj.service.Myconf;
import com.wangj.service.threadPool.ThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

@Service
@Order(value = 2)
public class ThreadPoolServiceImpl implements ThreadPoolService,ApplicationRunner {

    @Autowired
    private Myconf myconf;

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolServiceImpl.class);
    private static ThreadFactoryService threadFactory = new ThreadFactoryService("corePool");
    private static ExecutorService executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20));
    private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
    private static ExecutorService cacheExecutor = new ThreadPoolExecutor(0, 10, 60,
            TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory, new ThreadPoolExecutor.DiscardPolicy());

    @Scheduled(cron = "30 * * * * ?")
    @Override
    public void threadPoolTask() {
        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                private int i;

                public Runnable getInstacnce(int i) {
                    this.i = i;
                    return this;
                }

                @Override
                public void run() {
                    logger.info("task " + i + "........");
                }
            }.getInstacnce(i));
        }
    }

    @Override
    public void schedulerThreadPoolTask() {
        logger.info("coresize = " + myconf.getCoreSize());
        logger.info("maxsize = " + myconf.getMaxSize());
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LocalDateTime dateTime = LocalDateTime.now();
                logger.info(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        },0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void cacheThreadPoolTask() {
        for (int i = 0; i < 11; i++) {
            cacheExecutor.execute(new Runnable() {
                private int i;

                public Runnable getInstance(int i) {
                    this.i = i;
                    return this;
                }

                @Override
                public void run() {
                    logger.info("cache pool task " + i + "................");
                }
            }.getInstance(i));
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.cacheThreadPoolTask();
        this.schedulerThreadPoolTask();
    }
}
