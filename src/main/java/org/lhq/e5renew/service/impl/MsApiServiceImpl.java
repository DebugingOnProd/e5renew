package org.lhq.e5renew.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.setting.Setting;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.lhq.e5renew.dao.AzureParamDao;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.OutlookConfig;
import org.lhq.e5renew.service.MsApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MsApiServiceImpl implements MsApiService {
    @Resource
    AzureParamDao azureParamDao;
    @Override
    public OutlookConfig saveConfig(OutlookConfig config) {
        log.debug("接收到的参数:{}",config);
        AzureParam azureParam = new AzureParam();
        azureParam.setClientId(config.clientId());
        azureParam.setTenantId(config.tenantId());
        Setting setting = new Setting("RSA.setting");
        RSA rsa = new RSA(setting.getStr("PrivateKey"),setting.getStr("PublicKey"));
        byte[] encrypt =rsa.encrypt(StrUtil.bytes(config.clientSecret(), CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        azureParam.setClientSecret(Base64.encode(encrypt));
        azureParamDao.insert(azureParam);
        return config;
    }
}
