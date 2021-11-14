package com.sun.health.service.ocr;

import com.sun.health.comm.Const;
import com.sun.health.comm.DoStatus;
import com.sun.health.comm.Supplier;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.entity.ocr.OcrInfoEntity;
import com.sun.health.repository.ocr.OcrInfoRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.tencent.TencentService;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class OcrService extends AbstractService {

    @Autowired
    private OcrInfoRepository ocrInfoRepository;

    @Autowired
    private TencentService tencentService;

    /**
     * 加载报告图片并调用第三方ocr接口，将返回结果存储到数据库中
     */
    public void loadFileAndOcr() {
        String filePath = Const.imagePath;
        File parent = new File(filePath);
        File[] files = parent.listFiles();
        if (Objects.isNull(files)) {
            logger.warn("目录为空。文件路径为：{}", filePath);
            return;
        }
        for (File file : files) {

            OcrInfoEntity entity = new OcrInfoEntity();
            try {
                byte[] content = FileUtil.toByteArrayByNio(file);

                entity.setFileName(file.getName());
                entity.setContent(content);
                entity.setSupplier(Supplier.Tencent.name());
                entity.setStatus(DoStatus.todo.name());

                GeneralBasicOCRResponse response = tencentService.basicOcr(content);
                entity.setJsonResponse(JsonUtil.toJson(response));
                entity.setStatus(DoStatus.done.name());
            } catch (IOException e) {
                entity.setStatus(DoStatus.doing.name());
                logger.error("call tencent ocr api error.", e);
            }
            ocrInfoRepository.save(entity);
        }
    }

}
