package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.subject.OneSubject;
import com.nova.eduService.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-11-23
 */
@Api(description  = "课程分类管理")
@RestController
@RequestMapping("/eduService/eduSubject")
//@CrossOrigin
public class EduSubjectController {

    // 注入Service
    private final EduSubjectService eduSubjectService;

    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    // 课程分类列表
    @GetMapping("getAllSubject")
    @ApiOperation(value = "获取全部课程分类")
    public Result getAllSubject() {
        List<OneSubject> allSubject = eduSubjectService.getAllSubject();

        return Result.ok().data("SubjectList", allSubject);
    }

    // 添加课程分类
    @PostMapping("upload/excel")
    @ApiOperation(value = "导入课程分类(xlsx)")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.fetchFile(file, eduSubjectService);
        return Result.ok();
    }


}

