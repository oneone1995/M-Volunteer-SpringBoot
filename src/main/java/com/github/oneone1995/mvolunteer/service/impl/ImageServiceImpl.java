package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wangl on 2017/2/20.
 */
@Service
public class ImageServiceImpl implements ImageService {
    //文件上传目录的根路径
    @Value("${upload.path}")
    private String path;

    //文件上传具体目录
    @Value("${upload.dir}")
    private String dir;

    //服务器地址
    @Value("${server.host}")
    private String address;

    //服务器端口
    @Value("${server.port}")
    private String port;

    /**
     * 将上传的图片保存到服务器指定目录
     * @param file  客户端传输的文件
     * @return  服务器地址和文件名共同组成的字符串
     */
    @Override
    public String imageUpload(MultipartFile file) {
        // 获取文件名
        String filename = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));

        // 解决中文问题，linux下中文路径，图片显示问题
        filename = UUID.randomUUID() + suffixName;
        File dest = new File(path + dir + filename);
        try {
            file.transferTo(dest);
            //上传成功则返回服务器地址和文件名共同组成的字符串
            return "http://" + address + ":" + port + "/" + path + dir + filename;
        } catch (IOException e) {
            return null;
        }
    }
}
