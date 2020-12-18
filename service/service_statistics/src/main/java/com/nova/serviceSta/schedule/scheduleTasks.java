package com.nova.serviceSta.schedule;

import com.nova.serviceSta.service.StatisticsDailyService;
import com.nova.serviceSta.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class scheduleTasks {

    // 注入 service
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    // 定时生成统计数据
    @Scheduled(cron = "0 0 3 * * ? ")
    public void registerCounter() {
        // 每天凌晨三点生成前一天的统计数据
        statisticsDailyService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
