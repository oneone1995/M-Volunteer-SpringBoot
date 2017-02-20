package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by wangl on 2017/2/19.
 * 用于图片上传和头像修改
 */
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> imageUpload(
            @RequestParam(value = "file") MultipartFile file
            ) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.FILE_IS_EMPTY), HttpStatus.BAD_REQUEST);
        }
        String imgUrl;
        try {
            imgUrl = imageService.imageUpload(file);
        } catch (IOException e) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.FILE_UPLOAD_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResultModel.ok(imgUrl), HttpStatus.OK);
    }
}
