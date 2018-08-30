package com.zoro.springboot.timetask;

import lombok.extern.slf4j.Slf4j;

/**
 * @date 2018/8/30  17:38
 */
@Slf4j
public abstract class TaskRunnalbe implements Runnable {

    private String taskDesc="";

    public TaskRunnalbe(String taskDesc){
        this.taskDesc = taskDesc;
    }

    public abstract void runTask();

    @Override
    public void run() {
        try {
            log.info("定时任务{}开始", taskDesc);
            long begin = System.currentTimeMillis();
            runTask();
            log.info("任务{}结束，耗时：{}ms", taskDesc, (System.currentTimeMillis() - begin));
        }catch (Exception e){
            log.error("任务{}执行异常，错误信息：",taskDesc,e);
        }
    }
}
