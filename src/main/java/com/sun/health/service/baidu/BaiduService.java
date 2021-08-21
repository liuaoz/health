package com.sun.health.service.baidu;

import com.sun.health.config.BaiduConfig;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.service.AbstractService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesResponse;
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
public class BaiduService extends AbstractService {

    @Autowired
    private BaiduConfig baiduConfig;


    public void ocr(String imageUrl) {

        try {
            Credential cred = new Credential(baiduConfig.getSecretId(), baiduConfig.getSecretKey());
            OcrClient client = new OcrClient(cred, "ap-shanghai");
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
            try {
                req.setImageBase64(SafeUtil.base64Encode(FileUtil.read(imageUrl)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);
            System.out.println(DescribeInstancesResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
