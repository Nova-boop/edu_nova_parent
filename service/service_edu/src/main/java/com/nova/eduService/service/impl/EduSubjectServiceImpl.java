package com.nova.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.eduService.entity.EduSubject;
import com.nova.eduService.entity.excel.ExcelSubjectData;
import com.nova.eduService.entity.subject.OneSubject;
import com.nova.eduService.entity.subject.TwoSubject;
import com.nova.eduService.listener.SubjectExcelListener;
import com.nova.eduService.mapper.EduSubjectMapper;
import com.nova.eduService.service.EduSubjectService;
import com.nova.servicebase.exceptionhandler.NovaException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

        //1 获取文件输入流
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
            throw new NovaException(2001, "添加课程分类失败");
        }

    }

    @Override
    public List<OneSubject> getAllSubject() {
        // 获取一级分类数据
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        List<EduSubject> eduSubjectList = baseMapper.selectList(wrapper);

        // 获取二级分类数据
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper.ne("parent_id", 0);
        List<EduSubject> eduSubjectList2 = baseMapper.selectList(wrapper2);

        // 封装数据到结果数据集合中

        // 创建返回结果数据集合
        ArrayList<OneSubject> subjectArrayList = new ArrayList<>();

        // 遍历封装一级分类
        for (EduSubject eduSubject : eduSubjectList) {
            // 得到每个一级分类对象
            // 将以及分类对象放入到返回的结果对象中
            OneSubject oneSubject = new OneSubject();
            //  oneSubject.setId(eduSubject.getId());
            //  oneSubject.setTitle(eduSubject.getTitle());

            // 复制对象,BeanUtils.copyProperties,功能与上面类似
            BeanUtils.copyProperties(eduSubject, oneSubject);
            subjectArrayList.add(oneSubject);

            // 在一级分类中遍历二级分类
            ArrayList<TwoSubject> twoSubjectArrayList = new ArrayList<>();

            for (EduSubject eduSubject2 : eduSubjectList2) {
                if (eduSubject2.getParentId().equals(oneSubject.getId())) {
                    // 二级分类的getParentId 等于一级分类的id
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2, twoSubject);
                    twoSubjectArrayList.add(twoSubject);
                }
            }

            // 把二级分类放到一级分类中
            oneSubject.setChildren(twoSubjectArrayList);
        }
        // 返回数据
        return subjectArrayList;
    }


}


