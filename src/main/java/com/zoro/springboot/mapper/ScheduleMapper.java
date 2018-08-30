package com.zoro.springboot.mapper;


import com.zoro.springboot.entity.SchedulerConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    List<SchedulerConfig> getExcuteConfig();
    List<SchedulerConfig> findUpdateConfig();
    int updateSchedulerConfigDeal(int id);
}
