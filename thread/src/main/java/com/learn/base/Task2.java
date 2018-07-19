package com.learn.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task2 implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(Task2.class);

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        log.info("任务task2,threadId为" + current.getId());
    }
}
