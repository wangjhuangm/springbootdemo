package com.wangj.service.impl.threadPool;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryService implements ThreadFactory {

    private int num;
    private String name;

    public ThreadFactoryService(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-thread-" + num);
        num ++;
        return thread;
    }
}
