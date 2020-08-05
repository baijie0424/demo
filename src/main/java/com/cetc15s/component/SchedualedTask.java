//package com.cetc15s.component;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName SchedualedTask
// * @Description
// * @Author bj
// * @Date 2020/7/30 0:43
// * @Version 1.0
// */
//@Component
//public class SchedualedTask {
//    private static final Logger LOGGER = LoggerFactory.getLogger(SchedualedTask.class);
//
//    @Async("taskExecutor")
//    @Scheduled(initialDelay = 0,fixedDelay = 1000)
//    public void print() {
//        LOGGER.debug("定时任务执行了");
//        System.out.println("定时任务执行了");
//    }
//}
