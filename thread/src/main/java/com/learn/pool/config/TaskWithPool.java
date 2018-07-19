package com.learn.pool.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TaskWithPool {

    public static final Logger log = LoggerFactory.getLogger(TaskWithPool.class);

    @Resource
    private Executor threadPoolExecutor;

//    @Scheduled(cron = "0 * * * * ?")
    public void taskWithPool(){
        Task task = new Task("**用户积分结算**", true);
        Task task1 = new Task("**注水用户积分结算**", false);

        for (int i = 0;i < 5;i++){
            threadPoolExecutor.execute(task);
        }

        for (int i = 0;i < 10;i++){
            threadPoolExecutor.execute(task1);
        }

    }

   private class Task implements Runnable{

        private String taskName;

        private boolean flag;
        public Task(String taskName, boolean flag){
            this.taskName = taskName;
            this.flag = flag;
        }

        @Override
        public void run(){
            Thread current = Thread.currentThread();
            if (flag) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            log.info("=======任务" + taskName + "执行, 线程Id为" + current.getId());
        }
    }

}
