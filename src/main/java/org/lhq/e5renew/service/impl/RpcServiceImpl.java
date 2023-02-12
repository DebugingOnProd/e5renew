package org.lhq.e5renew.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.UserCollectionPage;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.lhq.e5renew.service.RpcSercvice;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class RpcServiceImpl implements RpcSercvice {
    public static final String scopes = "https://graph.microsoft.com/.default";

    public void call(AzureParamRecord azureParam) {
   /*     String clientId = azureParam.getClientId();
        String clientSecret = azureParam.getClientSecret();
        String tenantId = azureParam.getTenantId();*/
        String clientId = azureParam.clientId();
        String clientSecret = azureParam.clientSecret();
        String tenantId = azureParam.tenantId();
        TimeInterval timer = DateUtil.timer();
        timer.start();
        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder().clientId(clientId).clientSecret(clientSecret).tenantId(tenantId).build();
        TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(Collections.singletonList(scopes), clientSecretCredential);
        GraphServiceClient<Request> requestGraphServiceClient = GraphServiceClient.builder().authenticationProvider(tokenCredentialAuthProvider).buildClient();
        UserCollectionPage userCollectionPage = requestGraphServiceClient.users().buildRequest().get();
        List<User> userList = userCollectionPage.getCurrentPage();
        Map<String, String> userIdNameMap = userList.stream().collect(Collectors.toMap(item -> item.id, item -> item.displayName));
        Set<String> idSet = userList.stream().map(item -> item.id).collect(Collectors.toSet());
        log.info("idSet:{}", idSet);
        userList.forEach(item -> log.info("user:{},id:{}", item.displayName,item.id));
        for (String id : idSet) {
            MessageCollectionPage messageCollectionPage = requestGraphServiceClient.users(id).messages().buildRequest().get();
            List<Message> currentPage = messageCollectionPage.getCurrentPage();
            log.info("开始获取{}的邮件",userIdNameMap.get(id));
            for (Message message : currentPage) {
                String userName = userIdNameMap.get(id);
                log.info("名字:{},信息:{},{}",userName,message.subject,message.bodyPreview);
            }
        }
        long restart = timer.intervalRestart();
        log.info("耗时:{}",restart);
    }
}
