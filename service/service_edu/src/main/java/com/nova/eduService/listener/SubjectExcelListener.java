package com.nova.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.eduService.entity.EduSubject;
import com.nova.eduService.entity.excel.ExcelSubjectData;
import com.nova.eduService.service.EduSubjectService;
import com.nova.servicebase.exceptionhandler.NovaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    //因为SubjectExcelListener不能交给spring管理,需要自己new,不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService eduSubjectService;
    List<ExcelSubjectData> list = new ArrayList<ExcelSubjectData>();

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //读取excel内容,一行一行进行读取
    @Override
    public void invoke(ExcelSubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new NovaException(20001, "文件数据为空");
        }
        System.out.println(subjectData);
        list.add(subjectData);

        //一行一行读取,每次读取有两个值,第一个值为一级分类,第二个值为对应的二级分类
        EduSubject eduSubject = existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        //添加一级分类
        if (eduSubject == null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
        }
        //添加二级分类
        //获取一级分类的id值
        String pid = eduSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }
    }


    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", "0");
        return eduSubjectService.getOne(queryWrapper);
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", pid);
        return eduSubjectService.getOne(queryWrapper);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
