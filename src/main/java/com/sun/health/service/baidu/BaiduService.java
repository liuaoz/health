package com.sun.health.service.baidu;

import com.sun.health.config.BaiduConfig;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Service
public class BaiduService extends AbstractService {

    @Autowired
    private BaiduConfig baiduConfig;
}
