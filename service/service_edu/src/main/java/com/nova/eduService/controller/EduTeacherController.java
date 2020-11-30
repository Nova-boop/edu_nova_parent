package com.nova.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.Result;
import com.nova.eduService.entity.EduTeacher;
import com.nova.eduService.entity.vo.QueryTeacher;
import com.nova.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-11-13
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduService/eduTeacher")
//@CrossOrigin
public class EduTeacherController {

    // 将对应的service 注入进来
    private final EduTeacherService teacherService;

    public EduTeacherController(EduTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // 根据ID 查询讲师信息
    @ApiOperation(value = "根据讲师ID查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return Result.ok().data("teacher", teacher);
    }

    // 查询 edu_teacher 中所有的数据
    // restful 风格
    @GetMapping("finAllTeacher")
    @ApiOperation(value = "所有讲师列表")
    public Result findAllTeacher() {
        // 调用service 方法实现查询
        //  List<EduTeacher> list = teacherService.list(null);
        //  return list;
        List<EduTeacher> list = teacherService.list(null);
        return Result.ok().data("items", list);
    }

    // 根据ID逻辑删除讲师
    @DeleteMapping("removeTeacher/{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public Result removeTeacher(
            // todo 逻辑删除未实现
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    // 分页查询讲师
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师列表")
    public Result PageListTeacher(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit", value = "每页显示数量", required = true)
            @PathVariable Integer limit) {
        // 创建Page 对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);

        // 获取数据病组织
        IPage<EduTeacher> teacherIPage = teacherService.page(teacherPage, null);
        long total = teacherIPage.getTotal(); // 获取数据的总数
        List<EduTeacher> records = teacherIPage.getRecords(); // 获取每一页数据的列表
        //返回数据
        // return R.ok().data("total",total).data("items",records);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return Result.ok().data(map);
    }

    // 多条件组合查询

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "多条件查询讲师列表")
    public Result pageListTeacherCondition(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit", value = "每页显示数量", required = true)
            @PathVariable Integer limit,
            @RequestBody(required = false) QueryTeacher queryTeacher) {
        // 创建一个page 对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);

        // 构建多条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = queryTeacher.getName();
        Integer level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name); // 模糊查询
        }

        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        // 增加默认排序方法
        wrapper.orderByDesc("gmt_create");

        // 调用方法进行查询
        IPage<EduTeacher> teacherIPage = teacherService.page(eduTeacherPage, wrapper);

        long total = teacherIPage.getTotal(); // 获取数据的总数
        List<EduTeacher> records = teacherIPage.getRecords(); // 获取每一页数据的列表
        return Result.ok().data("total", total).data("items", records);
    }

    // 添加讲师的接口
    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public Result addTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        // 组织数据写入数据库
        boolean saveResult = teacherService.save(eduTeacher);

        // 返回结果
        if (saveResult) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(
            @RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);

        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

