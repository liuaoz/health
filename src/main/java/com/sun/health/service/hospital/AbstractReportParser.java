package com.sun.health.service.hospital;

import com.sun.health.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractReportParser extends AbstractService {

    /**
     * 解析报告内容
     */
    abstract void parse(byte[] content);

    /**
     * 修复差错（该差错是由于第三方文字识别结果不准确造成的）
     */
    abstract void repair();
}
