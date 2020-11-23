package com.nova.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.eduService.entity.EduSubject;
import com.nova.eduService.entity.excel.ExcelSubjectData;
import com.nova.eduService.service.EduSubjectService;
import com.nova.servicebase.exceptionhandler.NovaException;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    // SubjectExcelListener 没有交给spring 管理,使用有参构造注入service
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData==null) {
            throw new NovaException(20001,"表格数据不能为空");
        }
        // 添加一级分类
        EduSubject eduOneSubject = this.existOneSubject(eduSubjectService, excelSubjectData.getOneSubject());
        if (eduOneSubject==null) {
            eduOneSubject = new EduSubject();
            eduOneSubject.setParentId("0");
            eduOneSubject.setTitle(excelSubjectData.getOneSubject());
            eduSubjectService.save(eduOneSubject);
        }


        // 添加二级分类
        String pid = eduOneSubject.getId();

        EduSubject eduTwoSubject =this.existTwoSubject(eduSubjectService, excelSubjectData.getTwoSubject(),pid);
        if (eduTwoSubject==null) {
            eduTwoSubject = new EduSubject();
            eduTwoSubject.setParentId("0");
            eduTwoSubject.setTitle(excelSubjectData.getOneSubject());

            eduSubjectService.save(eduTwoSubject);
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    // 判断一级分类是否存在
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();

        eduSubjectQueryWrapper.eq("title", name);
        eduSubjectQueryWrapper.eq("parent_id", 0);
        return eduSubjectService.getOne(eduSubjectQueryWrapper);
    }

    // 判断二级分类是否存在

    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("title", name);
        eduSubjectQueryWrapper.eq("parent_id", pid);
        return eduSubjectService.getOne(eduSubjectQueryWrapper);
    }


}
