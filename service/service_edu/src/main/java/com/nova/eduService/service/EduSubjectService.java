package com.nova.eduService.service;

import com.nova.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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
}
