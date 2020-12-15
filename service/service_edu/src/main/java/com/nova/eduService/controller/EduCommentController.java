package com.nova.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.JwtUtils;
import com.nova.commonutils.Result;
import com.nova.commonutils.userVo.UcenterMemberVo;
import com.nova.eduService.client.UcenterClient;
import com.nova.eduService.entity.EduComment;
import com.nova.eduService.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-14
 */
@RestController
@RequestMapping("/eduService/eduComment")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 添加评论
     * 1. 添加评论
     * 2. 课程信息(课程id,讲师id)
     * 3. 获取用户信息
     */
    @PostMapping("addComment")
    public Result addComment(@RequestBody EduComment comment, HttpServletRequest request) {
        // 获取用户信息
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 判断用户信息是否为空
        if (StringUtils.isEmpty(memberId)) {
            return Result.error().code(20001).message("请登录再评论");
        }
        comment.setMemberId(memberId);
        // 根据用户Id获取用户信息
        UcenterMemberVo memberVo = ucenterClient.getUserInfo(memberId);

        comment.setNickname(memberVo.getNickname());
        comment.setAvatar(memberVo.getAvatar());
        commentService.save(comment);
        return Result.ok();
    }

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);

        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);

        commentService.page(pageParam, wrapper);
        List<EduComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return Result.ok().data(map);
    }

}

