package com.cetc15s.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName AsyncTask
 * @Description
 * @Author bj
 * @Date 2020/7/30 0:43
 * @Version 1.0
 */
@Component
@Async("asyncExecutor")
public class AsyncTask {

}
