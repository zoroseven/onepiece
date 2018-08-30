package com.zoro.springboot.entity;

/**
 * Created by nina on 2017/4/28.
 */
public class SchedulerConfig {
    private int id;
    private String taskName;
    private String taskMemo;
    private int isValid;
    private int isDealed;
    private String taskCron;

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskMemo() {
        return taskMemo;
    }

    public void setTaskMemo(String taskMemo) {
        this.taskMemo = taskMemo;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getIsDealed() {
        return isDealed;
    }

    public void setIsDealed(int isDealed) {
        this.isDealed = isDealed;
    }
}
