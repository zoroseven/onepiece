package com.zoro.springboot.timetask;

import com.zoro.springboot.entity.SchedulerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @date 2018/8/30  15:42
 */
//@Component
//@EnableScheduling
@Slf4j
public class ScheduleTask implements InitializingBean{

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private ScheduleService scheduleService;

    private boolean isStart = false;

    /**
     * spring 动态执行定时任务类
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("ScheduleTask 定时任务类初始化");
        scheduleService.init();
        start();
    }

    private void start(){
        threadPoolTaskScheduler.setPoolSize(3);
        for(SchedulerConfig config : scheduleService.getExcuteConfig()){
            TaskInfo t = scheduleService.getTaskList().get(config.getTaskName());
            if(t!=null){
                log.info("ScheduleTask 启动任务：{}",config.getTaskName());
                t.setCronTrigger(new CronTrigger(config.getTaskCron()));
                t.setFuture(threadPoolTaskScheduler.schedule(t.getTaskRunnalbe(),t.getCronTrigger()));
            }
        }
        isStart = true;
    }

    /**
     * 定时更新任务执行计划
     */
    @Scheduled(cron = "0 0/10 * * * *")
    public void testTimeTask(){
        if (isStart){
            for (SchedulerConfig config : scheduleService.findUpdateConfig()){
                TaskInfo task = scheduleService.getTaskList().get(config.getTaskName());
                if (task != null) {
                    if(config.getIsValid()==1){
                        if(task.getFuture()!=null){
                            task.getFuture().cancel(true);
                        }
                        task.setCronTrigger(new CronTrigger(config.getTaskCron()));
                        task.setFuture(threadPoolTaskScheduler.schedule(task.getTaskRunnalbe(), task.getCronTrigger()));
                        log.info("ScheduleTask 更新任务"+config.getTaskName()+" to:" + config.getTaskCron());
                    }else if(config.getIsValid()==0){
                        if(task.getFuture()!=null&&!task.getFuture().isCancelled()){
                            task.getFuture().cancel(true);
                        }
                        log.info("ScheduleTask 停止任务"+config.getTaskName());
                    }
                    scheduleService.updateSchedulerConfigDeal(config.getId());
                }
            }
        }
    }
}
