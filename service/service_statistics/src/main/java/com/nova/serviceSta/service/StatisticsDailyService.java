package com.nova.serviceSta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.serviceSta.entity.StatisticsDaily;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author nova
 * @since 2020-12-18
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    // 生成统计数据
    void createStatisticsByDay(String day);
}
