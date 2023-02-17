package org.lhq.e5renew.controller;

import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.lhq.e5renew.service.RpcSercvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    private final RpcSercvice rpcSercvice;

    public TestController(final RpcSercvice rpcSercvice) {
        this.rpcSercvice = rpcSercvice;
    }

    @PostMapping("bug")
    public AzureParamRecord test(@RequestBody AzureParamRecord azureParam){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->rpcSercvice.call(azureParam));
        return azureParam;
    }
}
