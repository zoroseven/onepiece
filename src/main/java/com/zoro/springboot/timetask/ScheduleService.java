package com.zoro.springboot.timetask;

import com.zoro.springboot.entity.SchedulerConfig;
import com.zoro.springboot.entity.Student;
import com.zoro.springboot.mapper.ScheduleMapper;
import com.zoro.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2018/8/30  17:34
 */
@Component
public class ScheduleService {

    /**
     * 定时任务列表
     */
    private Map<String,TaskInfo> taskList = new HashMap<>();

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScheduleMapper scheduleMapper;

    public Map<String, TaskInfo> getTaskList() {
        return taskList;
    }

    /**
     * 获取数据库定时任务列表
     */
    public List<SchedulerConfig> getExcuteConfig(){
        return scheduleMapper.getExcuteConfig();
    }
    /**
     * 获取数据库定时任务列表
     */
    public List<SchedulerConfig> findUpdateConfig(){
        return scheduleMapper.findUpdateConfig();
    }

    public int updateSchedulerConfigDeal(int id) {
        return scheduleMapper.updateSchedulerConfigDeal(id);
    }
    /**
     * 初始化定时任务列表
     */
    public void init(){
        TaskInfo test = new TaskInfo();
        test.setTaskRunnalbe(new TaskRunnalbe("studentService") {
            @Override
            public void runTask() {
                List<Student> studentList = studentService.getStudentList();
                System.out.println(studentList);
            }
        });
        taskList.put("studentService",test);
    }
}
