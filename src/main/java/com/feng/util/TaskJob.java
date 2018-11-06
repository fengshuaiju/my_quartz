package com.feng.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskJob {

//    @Scheduled(cron = "0/5 * * * * ?")
    public void job1() {
        log.info("任务进行中！！s11s！！！！ss");
    }
}  
