package com.sun.health.controller.bandao.file;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.entity.bandao.file.FileEntity;
import com.sun.health.service.bandao.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

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

    @GetMapping(value = "/{fileId}")
    public void download(@PathVariable Long fileId, HttpServletResponse resp) {

        FileEntity fileEntity = fileService.findById(fileId);
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write(new String(fileEntity.getContent()));
            resp.setContentType(fileEntity.getFileType()+";charset=utf-8");
            writer.flush();
        } catch (IOException e) {
            logger.error("download file error.", e);
        } finally {
            if (Objects.nonNull(writer)) {
                writer.close();
            }
        }
    }

    /**
     * 根据id获取图片
     */
    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        FileEntity fileEntity = fileService.findById(id);
        return fileEntity.getContent();
    }
}
