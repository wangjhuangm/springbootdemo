package com.wangj.service.lock;

public interface LockService {

    void task1() throws InterruptedException;

    void task2() throws InterruptedException;
}
