package org.lhq.e5renew.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.e5renew.entity.OutlookConfig;
import org.lhq.e5renew.service.MsApiService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MsApiServiceImpl implements MsApiService {
    @Override
    public OutlookConfig saveConfig(OutlookConfig config) {
        log.debug("接收到的参数:{}",config);
        return config;
    }
}
