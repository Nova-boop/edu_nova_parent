package com.nova.eduOrder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduOrder.entity.TPayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
public interface TPayLogService extends IService<TPayLog> {
    // 生成微信支付二维码
    Map<String, String> createNative(String orderNo);

    // 查询支付状态
    Map<String, String> queryPayStatus(String orderNo);

    // 更新订单状态
    void updateOrderStatus(Map<String, String> map);
}
