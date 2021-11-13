package com.sun.health.service.tencent;

import com.sun.health.config.TencentConfig;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.service.AbstractService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;
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

    private OcrClient create() {
        Credential cred = new Credential(tencentConfig.getSecretId(), tencentConfig.getSecretKey());
        return new OcrClient(cred, "ap-shanghai");
    }

    public GeneralBasicOCRResponse basicOcr(byte[] content) {

        GeneralBasicOCRResponse resp = null;
        try {
            OcrClient client = create();
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
            req.setImageBase64(SafeUtil.base64Encode(content));
            resp = client.GeneralBasicOCR(req);
            logger.info(GeneralBasicOCRResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            logger.error("tencent basic ocr error.", e);
        }
        return resp;
    }

    public GeneralAccurateOCRResponse accurateOcr(byte[] content) {

        GeneralAccurateOCRResponse resp = null;
        try {
            OcrClient client = create();
            GeneralAccurateOCRRequest req = new GeneralAccurateOCRRequest();
            req.setImageBase64(SafeUtil.base64Encode(content));
            resp = client.GeneralAccurateOCR(req);
            logger.info(GeneralBasicOCRResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            logger.error("tencent accurate ocr error.", e);
        }
        return resp;
    }

    public AdvertiseOCRResponse advertiseOcr(byte[] content){
        AdvertiseOCRResponse resp = null;
        try {
            OcrClient client = create();
            AdvertiseOCRRequest req = new AdvertiseOCRRequest();
            req.setImageBase64(SafeUtil.base64Encode(content));
            resp = client.AdvertiseOCR(req);
            logger.info(AdvertiseOCRResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            logger.error("tencent advertise ocr error.", e);
        }
        return resp;
    }
}
