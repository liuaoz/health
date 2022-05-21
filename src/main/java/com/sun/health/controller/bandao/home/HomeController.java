package com.sun.health.controller.bandao.home;

import com.sun.health.comm.FileCategory;
import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.entity.bandao.file.FileEntity;
import com.sun.health.service.bandao.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Autowired
    private FileService fileService;


    @GetMapping("/banner/images")
    public JsonRet<List<Long>> getHomeBannerImages() {
        List<FileEntity> fileEntities = fileService.getByFileCategoryWithoutContent(FileCategory.HOME_BANNER);
        List<Long> fileIds = fileEntities.stream().map(FileEntity::getId).collect(Collectors.toList());
        return JsonRet.success(fileIds);
    }

    @GetMapping("/item/images")
    public JsonRet<List<Long>> getHomeItemImages() {
        List<FileEntity> fileEntities = fileService.getByFileCategoryWithoutContent(FileCategory.HOME_ITEM_CLASSIFICATION);
        List<Long> fileIds = fileEntities.stream().map(FileEntity::getId).collect(Collectors.toList());
        return JsonRet.success(fileIds);
    }
}
