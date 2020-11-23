package com.nova.eduService.controller;


import com.nova.commonutils.Result;
import com.nova.eduService.entity.subject.OneSubject;
import com.nova.eduService.service.EduSubjectService;
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
@RestController
@RequestMapping("/eduService/eduSubject")
@CrossOrigin
public class EduSubjectController {

    // 注入Service
    @Autowired
    private EduSubjectService eduSubjectService;

    // 课程分类列表
    @GetMapping("getSubject")
    public Result getAllSubject() {
        List<OneSubject> allSubject = eduSubjectService.getAllSubject();

        return Result.ok().data("SubjectList", allSubject);
    }

    // 添加课程分类
    @PostMapping("upload/excel")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.fetchFile(file, eduSubjectService);
        return Result.ok();
    }


}

