package com.nova.eduOrder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduOrder.entity.TOrder;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
public interface TOrderService extends IService<TOrder> {
    // 生成订单(根据课程ID和用户ID生成订单)
    String createOrder(String courseId, String memberIdByJwtToken);

}
