package com.nova.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.eduService.entity.EduSubject;
import com.nova.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author nova
 * @since 2020-11-23
 */
public interface EduSubjectService extends IService<EduSubject> {

    void fetchFile(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}
