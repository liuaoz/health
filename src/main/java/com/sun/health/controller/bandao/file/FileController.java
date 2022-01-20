package com.sun.health.controller.bandao.file;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.entity.bandao.file.FileEntity;
import com.sun.health.service.bandao.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public JsonRet<Boolean> uploadFile(@RequestParam("files") MultipartFile[] files) {
        boolean result = true;
        for (MultipartFile file : files) {
            boolean b = fileService.uploadFile(file);
            if (!b) {
                result = false;
            }
        }
        return JsonRet.success(result);
    }

    /**
     * 根据id获取文件
     */
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getFile(@PathVariable Long id) {
        FileEntity fileEntity = fileService.findById(id);
        return fileEntity.getContent();
    }
}
