package com.sun.health.dto.bandao.good;

import com.sun.health.dto.BaseDTO;

/**
 * 商品的图片名称
 */
public class GoodImagesDto extends BaseDTO {

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
