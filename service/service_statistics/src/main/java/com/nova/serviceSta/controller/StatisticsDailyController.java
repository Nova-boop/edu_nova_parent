package com.nova.serviceSta.controller;


import com.nova.commonutils.Result;
import com.nova.serviceSta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/serviceSta/staDaily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    // 生成统计数据
    @PostMapping("createSta/{day}")
    public Result createSta(@PathVariable String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return Result.ok();
    }

    // 获取统计图表数据
    @GetMapping("showData/{type}/{start}/{end}")
    public Result showData(@PathVariable String type,
                           @PathVariable String start,
                           @PathVariable String end) {
        Map<String,Object> map = statisticsDailyService.getShowData(type, start, end);
        return Result.ok().data(map);
    }


}

