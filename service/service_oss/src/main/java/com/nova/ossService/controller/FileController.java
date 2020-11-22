package com.nova.ossService.controller;

import com.nova.commonutils.R;
import com.nova.ossService.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Api(description = "文件上传")
@RequestMapping("eduOss/file")
@RestController
@CrossOrigin
public class FileController {

//    @Autowired
//    private FileService fileService;

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("upload")
    public R OssUpload(MultipartFile file ) throws FileNotFoundException {
        String url = fileService.upload(file);
        return R.ok().message("上传成功").data("url",url);
    }

}
