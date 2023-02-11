package org.lhq.e5renew.entity;


public record OutlookConfig(
        String clientId,
        String clientSecret,
        int outlookId,
        String tenantId) {

}
