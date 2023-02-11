package org.lhq.e5renew.service;

import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;

public interface RpcSercvice {
    void call(AzureParamRecord azureParam);
}
