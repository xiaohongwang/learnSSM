package com.learn.base;

import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Baselean {
    private static final Logger log = LoggerFactory.getLogger(Baselean.class);

//    @Scheduled(cron = "*/2 * * * * ?")
    public void teamThread(){
        try {
            CountDownLatch latch = new CountDownLatch(1);
            Task3 first = new Task3("first", latch);
            first.start();
            latch.await();
            latch = new CountDownLatch(2);
            Task3 second = new Task3("second", latch);
            Task3 third = new Task3("third", latch);
            second.start();
            third.start();
            latch.await();
            Task3 last = new Task3("last",latch);
            last.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

//    @Scheduled(cron = "*/5 * * * * ?")
    public void entrance(){

        log.info("这是学习的入口");
        Task1 task1 = new Task1();
        task1.start();

        Task2 task2 = new Task2();
        new Thread(task2).start();
        new Thread(task2).start();

    }


}
