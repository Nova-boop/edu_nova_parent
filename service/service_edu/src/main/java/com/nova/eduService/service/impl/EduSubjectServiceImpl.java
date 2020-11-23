package com.nova.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduSubject;
import com.nova.eduService.entity.excel.ExcelSubjectData;
import com.nova.eduService.listener.SubjectExcelListener;
import com.nova.eduService.mapper.EduSubjectMapper;
import com.nova.eduService.service.EduSubjectService;
import com.nova.servicebase.exceptionhandler.NovaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-11-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void fetchFile(MultipartFile file, EduSubjectService eduSubjectService) {
        // 导入Excel 完成课程分类的添加
        // 导入Excel 实现文件读取的文件流
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NovaException(2000, "添加课程分类失败");
        }

    }

}
