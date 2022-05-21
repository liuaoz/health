package com.sun.health.service.bandao.file;

import com.sun.health.comm.FileCategory;
import com.sun.health.core.comm.CheckSumAlgoType;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.bandao.file.FileEntity;
import com.sun.health.repository.bandao.file.FileRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileService extends AbstractService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity findById(Long id) {
        return fileRepository.getById(id);
    }

    public void save(FileEntity entity) {
        fileRepository.save(entity);
    }

    public List<FileEntity> getByFileCategoryWithoutContent(FileCategory fileCategory) {
        List<Map<String, Object>> mapList = fileRepository.findSimpleByFileCategory(fileCategory.name());
        return mapList.stream().map(t -> JsonUtil.fromJson(JsonUtil.toJson(t), FileEntity.class)).collect(Collectors.toList());
    }

    public boolean uploadFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        byte[] content;

        try {
            InputStream is = file.getInputStream();
            content = FileUtil.inputStream2ByteArray(is);
        } catch (Exception e) {
            logger.error("upload file error.fileName=" + filename, e);
            return false;
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(filename);
        fileEntity.setFileType(file.getContentType());
        fileEntity.setContent(content);
        fileEntity.setUuid(StringUtil.rand32Str());
        fileEntity.setSize(content.length);
        try {
            fileEntity.setHash(SafeUtil.hash(content, CheckSumAlgoType.MD5.getName()));
        } catch (NoSuchAlgorithmException e) {
            logger.warn("generate file hash warn.fileName={}", filename);
        }
        fileRepository.save(fileEntity);
        return true;
    }

}
