package com.leyou.upload.web;

import com.leyou.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
// 整个路径只需要经过zuul但是不需要缓存，故请求路径最前面要加上zuul(spring-cloud官方文档有说明 推荐这样做)
//  此种方式需要经过nginx rewrite  改变请求路径。
//   location /api/upload {
//    rewrite "^/(.*)$ /zuul/$1;
//   }
//   原本如： api.leyou.com/api/upload/image  修改为：  api.leyou.com/zuul/api/upload/image
//   此种方式不在zuul中进行缓存了
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(uploadService.uploadImage(file));
    }
}
