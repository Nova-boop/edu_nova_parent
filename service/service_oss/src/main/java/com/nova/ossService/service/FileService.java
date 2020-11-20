package com.nova.ossService.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {

    String upload(MultipartFile file) throws FileNotFoundException;
}
