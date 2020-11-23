package com.nova.eduService.controller;


import com.nova.commonutils.R;
import com.nova.eduService.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    private final EduSubjectService eduSubjectService;

    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    // 添加课程分类
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.fetchFile(file,eduSubjectService);

        return R.ok();
    }

}

