package com.sun.health.entity.ocr;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ocr_info")
public class OcrInfoEntity extends BaseEntity {

    private String fileName;

    private byte[] content;

    private String textDetections;

    private boolean translated;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTextDetections() {
        return textDetections;
    }

    public void setTextDetections(String textDetections) {
        this.textDetections = textDetections;
    }

    public boolean isTranslated() {
        return translated;
    }

    public void setTranslated(boolean translated) {
        this.translated = translated;
    }
}
