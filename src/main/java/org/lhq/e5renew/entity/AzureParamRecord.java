package org.lhq.e5renew.entity;

public record AzureParamRecord(String clientId,String clientSecret,String tenantId) {
    public AzureParam toEntity(){
        AzureParam azureParam = new AzureParam();
        azureParam.setClientId(clientId);
        azureParam.setClientSecret(clientSecret);
        azureParam.setTenantId(tenantId);
        return azureParam;
    }
}
