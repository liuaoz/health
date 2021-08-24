package com.sun.health.service.tencent;

import com.sun.health.config.TencentConfig;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.service.AbstractService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Service
public class TencentService extends AbstractService {

    @Autowired
    private TencentConfig tencentConfig;

    public GeneralBasicOCRResponse ocr(byte[] content) {

        GeneralBasicOCRResponse resp = null;
        try {
            Credential cred = new Credential(tencentConfig.getSecretId(), tencentConfig.getSecretKey());
            OcrClient client = new OcrClient(cred, "ap-shanghai");
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
            req.setImageBase64(SafeUtil.base64Encode(content));
            resp = client.GeneralBasicOCR(req);
            logger.info(GeneralBasicOCRResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }
}
