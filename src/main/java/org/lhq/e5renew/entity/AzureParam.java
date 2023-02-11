package org.lhq.e5renew.entity;

import lombok.Data;

@Data
public class AzureParam {
    private String clientId;
    private String clientSecret;
    private String tenantId;
}
