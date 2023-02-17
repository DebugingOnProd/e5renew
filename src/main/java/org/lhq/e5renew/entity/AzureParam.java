package org.lhq.e5renew.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AzureParam {
    private Long id;
    private String clientId;
    private String clientSecret;
    private String tenantId;
}
