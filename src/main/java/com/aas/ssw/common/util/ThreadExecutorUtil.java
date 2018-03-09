package com.aas.ssw.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/**
 * 线程工具类
 * @author xl
 */
@Component
public class ThreadExecutorUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadExecutorUtil.class);

    private static Executor executor;

    /**
     * 并发执行任务
     * @param tasks 要执行的任务列表
     * @param taskNames 任务名称
     * @return key:任务名称;value:任务执行结果
     */
    public static Map<String,Object> concurrentExcute(FutureTask[] tasks,String[] taskNames){
        try {
            Map<String,Object> result = new HashMap<>();
            for (FutureTask task : tasks) {
                executor.execute(task);
            }
            for (int i = 0; i < taskNames.length; i++) {
                result.put(taskNames[i],tasks[i].get());
            }
            return result;
        } catch (InterruptedException e) {
            LOGGER.error("并发执行异常！",e);
            return null;
        } catch (ExecutionException e) {
            LOGGER.error("并发执行异常！",e);
            return null;
        }
    }

    @Resource
    public void setExecutor(Executor executor) {
        ThreadExecutorUtil.executor = executor;
    }
}
