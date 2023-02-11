package org.lhq.e5renew.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lhq.e5renew.dao.ParamDao;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.lhq.e5renew.service.AzureParamService;
import org.springframework.stereotype.Service;

@Service
public class AzureParamServiceImpl implements AzureParamService {
    private final ParamDao paramDao;

    public AzureParamServiceImpl(final ParamDao paramDao) {
        this.paramDao = paramDao;
    }

    @Override
    public void save(AzureParamRecord azureParamRecord) {
        AzureParam azureParam = azureParamRecord.toEntity();
        paramDao.insert(azureParam);
    }

    public IPage<AzureParam> selectPage(Integer pageNum,Integer pageSize){
        IPage<AzureParam> page = new Page<>(pageNum,pageSize);
        return paramDao.selectPage(page,null);
    }

}
