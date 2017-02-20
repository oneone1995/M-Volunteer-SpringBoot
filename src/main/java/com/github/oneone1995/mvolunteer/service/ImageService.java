package com.github.oneone1995.mvolunteer.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by wangl on 2017/2/20.
 * 处理图片及头像的业务代码接口
 */
public interface ImageService {
    String imageUpload(MultipartFile file) throws IOException;
}
