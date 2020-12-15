package com.nova.eduOrder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.commonutils.courseVo.CourseWebOrderVo;
import com.nova.commonutils.userVo.UcenterMemberVo;
import com.nova.eduOrder.client.CourseClient;
import com.nova.eduOrder.client.UcenterClient;
import com.nova.eduOrder.entity.TOrder;
import com.nova.eduOrder.mapper.TOrderMapper;
import com.nova.eduOrder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    // 注入service
    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UcenterClient ucenterClient;


    // 生成订单(根据课程ID和用户ID生成订单)
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {

        // 根据用户ID远程调用获取用户信息
        UcenterMemberVo ucenterMemberVo = ucenterClient.getUserInfo(memberIdByJwtToken);

        // 根据课程ID远程调用获取课程信息
        CourseWebOrderVo courseInfoOrderVo = courseClient.getCourseInfoOrder(courseId);

        // 创建订单

        return null;
    }
}
