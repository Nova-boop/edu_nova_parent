package com.nova.eduOrder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.commonutils.courseVo.CourseWebOrderVo;
import com.nova.commonutils.userVo.UcenterMemberVo;
import com.nova.eduOrder.client.CourseClient;
import com.nova.eduOrder.client.UcenterClient;
import com.nova.eduOrder.entity.TOrder;
import com.nova.eduOrder.mapper.TOrderMapper;
import com.nova.eduOrder.service.TOrderService;
import com.nova.eduOrder.utils.OrderNoUtil;
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

    // 课程微服务
    private final CourseClient courseClient;

    // 用户微服务
    private final UcenterClient ucenterClient;

    public TOrderServiceImpl(CourseClient courseClient, UcenterClient ucenterClient) {
        this.courseClient = courseClient;
        this.ucenterClient = ucenterClient;
    }


    // 生成订单(根据课程ID和用户ID生成订单)
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {

        // 根据用户ID远程调用获取用户信息
        UcenterMemberVo ucenterMemberVo = ucenterClient.getUserInfo(memberIdByJwtToken);
        // 根据课程ID远程调用获取课程信息
        CourseWebOrderVo courseInfoOrderVo = courseClient.getCourseInfoOrder(courseId);

        // 创建订单
        TOrder order = new TOrder();

        // 设置订单信息
        order.setOrderNo(OrderNoUtil.getOrderNo()); // 订单号
        order.setTotalFee(courseInfoOrderVo.getPrice());
        order.setStatus(0);
        order.setPayType(1);

        // 设置课程
        order.setCourseId(courseInfoOrderVo.getId());
        order.setCourseTitle(courseInfoOrderVo.getTitle());
        order.setCourseCover(courseInfoOrderVo.getCover());
        order.setTeacherName(courseInfoOrderVo.getTeacherName());

        // 设置用户信息
        order.setMemberId(ucenterMemberVo.getId());
        order.setMobile(ucenterMemberVo.getMobile());
        order.setNickname(ucenterMemberVo.getNickname());


        // 插入记录
        baseMapper.insert(order);

        // 返回订单号
        return order.getOrderNo();
    }
}
