package com.sun.health.service.tencent;

import com.sun.health.config.TencentConfig;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.service.AbstractService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Service
public class TencentService extends AbstractService {

    @Autowired
    private TencentConfig tencentConfig;

    public GeneralBasicOCRResponse ocr(String imageUrl) {

        GeneralBasicOCRResponse resp = null;
        try {
            Credential cred = new Credential(tencentConfig.getSecretId(), tencentConfig.getSecretKey());
            OcrClient client = new OcrClient(cred, "ap-shanghai");
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
            try {
                req.setImageBase64(SafeUtil.base64Encode(FileUtil.read(imageUrl)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            resp = client.GeneralBasicOCR(req);
            logger.info(GeneralBasicOCRResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }
}
