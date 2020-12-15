package com.nova.eduService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduComment;
import com.nova.eduService.mapper.EduCommentMapper;
import com.nova.eduService.service.EduCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
}
