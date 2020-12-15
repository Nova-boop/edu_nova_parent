package com.nova.eduOrder.controller;


import com.nova.commonutils.JwtUtils;
import com.nova.commonutils.Result;
import com.nova.eduOrder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
@RestController
@RequestMapping("/eduOrder/Order")
public class TOrderController {
    @Autowired
    private TOrderService orderService;

    // 生成订单(根据课程ID和用户ID生成订单)
    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Result.ok().data("orderId", orderId);
    }

    // todo 根据订单ID 查询订单详情
    @GetMapping("getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable String orderId) {


        return Result.ok();
    }

    // todo 生成微信支付二维码接口

    // todo 查询订单支付状态接口


}

