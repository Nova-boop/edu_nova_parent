package com.nova.eduService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String OneSubjectName;   // 一级分类
    @ExcelProperty(index = 1)
    private String TwoSubjectName;   // 二级分类
}
