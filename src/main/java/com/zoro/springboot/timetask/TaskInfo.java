package com.zoro.springboot.timetask;

import lombok.Data;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.Future;

/**
 * @date 2018/8/30  17:30
 */
@Data
public class TaskInfo {

    private CronTrigger cronTrigger;

    private TaskRunnalbe taskRunnalbe;

    private Future<?> future;
}
