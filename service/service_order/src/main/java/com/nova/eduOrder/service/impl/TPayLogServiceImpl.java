package com.nova.eduOrder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduOrder.entity.TPayLog;
import com.nova.eduOrder.mapper.TPayLogMapper;
import com.nova.eduOrder.service.TPayLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

}
