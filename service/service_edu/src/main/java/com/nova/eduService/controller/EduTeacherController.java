package com.nova.eduService.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.commonutils.R;
import com.nova.eduService.entity.EduTeacher;
import com.nova.eduService.service.EduTeacherService;
import io.swagger.annotations.*;
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
public class EduTeacherController {

    // 将对应的service 注入进来
//    @Autowired
//    private EduTeacherService teacherService;

    private final EduTeacherService teacherService;

    public EduTeacherController(EduTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // 查询 edu_teacher 中所有的数据
    // restful 风格
    @GetMapping("finAllTeacher")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher() {
        // 调用service 方法实现查询
        //  List<EduTeacher> list = teacherService.list(null);
        //  return list;
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    // 根据ID逻辑删除讲师
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 分页查询讲师
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师列表")
    public R PageListTeacher(
            @PathVariable Integer current,
            @PathVariable Integer limit) {
        // 创建Page 对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);

        // 获取数据病组织
        IPage<EduTeacher> teacherIPage = teacherService.page(teacherPage, null);
        long total = teacherIPage.getTotal(); // 获取数据的总行数
        List<EduTeacher> records = teacherIPage.getRecords(); // 获取每一页数据的列表
        //返回数据
        // return R.ok().data("total",total).data("items",records);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
}

