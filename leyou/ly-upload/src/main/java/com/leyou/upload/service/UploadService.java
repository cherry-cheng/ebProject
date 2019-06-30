package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    @Autowired
    private UploadProperties prop;

    @Autowired
    private FastFileStorageClient storageClient;
    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if (!prop.getAllowTypes().contains(contentType)) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            // 准备目标路径
            File dest = new File("D:\\cyh\\java\\javapoj\\IdeaProjects\\ebProject\\upload", file.getOriginalFilename());
            // 保存文件到本地
            file.transferTo(dest);
            return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561893807614&di=6c15d64fd3c561fbc2fb9fcdb5ab73bc&imgtype=0&src=http%3A%2F%2F04imgmini.eastday.com%2Fmobile%2F20190627%2F20190627170441_ebd475ec985980af90ef3371018e7dcb_2.jpeg";
            // 配置好文件分布式系统之后，通过nginx转发，能匹配到图片之后。由fdfs实现
            // 上传到FastDFS
            /*String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 返回路径
            return prop.getBaseUrl() + storePath.getFullPath();*/
        } catch (IOException e) {
            // 上传文件失败
            log.error("上传文件失败！", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}
