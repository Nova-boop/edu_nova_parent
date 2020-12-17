package com.nova.eduOrder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.commonutils.JwtUtils;
import com.nova.commonutils.Result;
import com.nova.eduOrder.entity.TOrder;
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
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId, memberIdByJwtToken);
        return Result.ok().data("orderNo", orderNo);
    }

    //根据订单编号 查询订单详情
    @GetMapping("getOrderInfo/{orderNo}")
    public Result getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<TOrder> tOrderQueryWrapper = new QueryWrapper<>();
        tOrderQueryWrapper.eq("order_no", orderNo);
        TOrder order = orderService.getOne(tOrderQueryWrapper);
        return Result.ok().data("order", order);
    }

    // 判断用户是否购买课程
    @GetMapping("isBuyCourse/{courseId}/{userId}")
    public Boolean isBuyCourse(@PathVariable String courseId, @PathVariable String userId) {

        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", userId);
        wrapper.eq("status", 1);

        int orderCount = orderService.count(wrapper);
        return orderCount > 0;
    }
}

