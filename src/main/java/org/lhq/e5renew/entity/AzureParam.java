package org.lhq.e5renew.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lhq.e5renew.config.anno.Sensitive;

@Data
@TableName("azure_param")
public class AzureParam {
    private Long id;
    private String clientId;

    @Sensitive(prefixNoMaskLen = 4,suffixNoMaskLen = 4)
    private String clientSecret;
    private String tenantId;
}
