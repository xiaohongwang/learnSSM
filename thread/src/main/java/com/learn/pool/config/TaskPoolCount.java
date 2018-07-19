package com.learn.pool.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskPoolCount {
    public static final Logger log = LoggerFactory.getLogger(TaskPoolCount.class);

    @Resource
    private Executor taskExecutor;

    @Scheduled(cron = "*/5 * * * * ?")
    public void dealTask(){
        CountDownLatch latch = new CountDownLatch(2);
        String threadName =  "first";
        Task firstTask = new Task(threadName, latch);
        threadName = "second";
        Task secondTask = new Task(threadName, latch);

        taskExecutor.execute(firstTask);
        taskExecutor.execute(secondTask);

        try {
//            latch.await();
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("==== 主线程继续执行 ====");

    }

    private class Task implements Runnable{

        private String threadName;

        private CountDownLatch latch;

        public Task(String threadName, CountDownLatch latch ){
            this.threadName = threadName;
            this.latch = latch;
        }

        @Override
        public void run() {
            Thread current = Thread.currentThread();
            log.info("====线程 :" +  threadName + "执行开始，ID为" + current.getId());

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0;i <8; i++){
                log.info("=====" + threadName + "任务==========" + i);
            }
            latch.countDown();
        }
    }
}
