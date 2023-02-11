package org.lhq.e5renew.service.impl;

import com.azure.core.http.HttpClient;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.httpcore.HttpClients;
import com.microsoft.graph.models.Authentication;
import com.microsoft.graph.models.IdentityContainer;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.UserCollectionPage;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.lhq.e5renew.service.RpcSercvice;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


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
        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();
        TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(
                Collections.singletonList(scopes),
                clientSecretCredential);
        GraphServiceClient<Request> requestGraphServiceClient = GraphServiceClient.builder()
                .authenticationProvider(tokenCredentialAuthProvider).buildClient();
        UserCollectionPage userCollectionPage = requestGraphServiceClient.users().buildRequest().get();
        List<User> userList = userCollectionPage.getCurrentPage();
        userList.forEach(item -> log.info("user:{}", item.displayName));
    }
}
