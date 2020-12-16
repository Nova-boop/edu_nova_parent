package com.nova.eduOrder.controller;


import com.nova.commonutils.Result;
import com.nova.eduOrder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
@RestController
@RequestMapping("/eduOrder/payLog")
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;

    // 生成微信支付二维码
    @GetMapping("createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo){
        // 返回结果有二维码地址和其他信息
        Map<String,String> map = payLogService.createNative(orderNo);
        return Result.ok().data("map",map);
    }


    // 查询支付状态

}

