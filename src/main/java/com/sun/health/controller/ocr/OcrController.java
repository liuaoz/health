package com.sun.health.controller.ocr;

import com.sun.health.core.comm.JsonRet;
import com.sun.health.service.ocr.OcrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private OcrInfoService ocrService;

    @GetMapping("/loadFileAndOcr")
    public JsonRet<Boolean> loadFileAndOcr() {
        ocrService.loadFileAndOcr();
        return JsonRet.success();
    }
}
