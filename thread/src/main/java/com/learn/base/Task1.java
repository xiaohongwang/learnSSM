package com.learn.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task1 extends Thread{
    private static final Logger log = LoggerFactory.getLogger(Task1.class);

    @Override
    public void run() {
        Thread current = Thread.currentThread();

        log.info("===========任务task1执行开始=======");
        for (int i = 0;i <8; i++){
            log.info("==========task1" + current.getName() + "任务==========" + i);
        }
        log.info("任务task1,threadId为" + current.getId());
        log.info("===========任务task1执行开始=======");
    }
}
