package com.wangj.service.impl.lock;

import com.wangj.service.lock.LockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockServiceImpl implements LockService {

    private final static Logger logger = LoggerFactory.getLogger(LockServiceImpl.class);
    private Object lock = new Object();
    private boolean flag = true;

    @Override
    public void task1() throws InterruptedException {
        for (int i = 0; i < 50; i ++) {
            synchronized (lock) {
                System.out.println("task1 aquired lock");
                if (flag) {
                    System.out.println("task1_" + i + "............");
                    flag = false;
                    lock.notify();
                    if (i < 49) {
                        lock.wait();
                    }
                }
            }
        }
    }

    @Override
    public void task2() throws InterruptedException {
        for (int i = 0; i < 50; i ++) {
            synchronized (lock) {
                System.out.println("task2 aquired lock");
                if (!flag) {
                    System.out.println("task2_" + i + "............");
                    flag = true;
                    lock.notify();
                    if (i < 49) {
                        lock.wait();
                    }
                }else {
                    lock.wait();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        LockServiceImpl impl = new LockServiceImpl();
        Thread task1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    impl.task1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread task2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    impl.task2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("===============================");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task1);
        executorService.execute(task2);
    }
}
