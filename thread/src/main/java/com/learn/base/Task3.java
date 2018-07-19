package com.learn.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Task3 extends Thread{

    private static final Logger log = LoggerFactory.getLogger(Task1.class);

    private CountDownLatch latch;

    public Task3(String name,CountDownLatch latch){
        super(name);
        this.latch = latch;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        log.info("============"+current.getName() + "任务执行开始==============");
        for (int i = 0;i <8; i++){
            log.info("==========" + current.getName() + "任务==========" + i);
        }
        log.info("==========="+current.getName() + "任务执行结束===============");
        latch.countDown();
    }
}
