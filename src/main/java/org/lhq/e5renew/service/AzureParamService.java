package org.lhq.e5renew.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;

public interface AzureParamService {
    void save(AzureParamRecord azureParamRecord);

    IPage<AzureParam> selectPage(Integer pageNum,Integer pageSize);
}
