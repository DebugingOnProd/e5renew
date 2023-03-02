package org.lhq.e5renew.controller;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class TestControllerTest {

    @Resource
    private MockMvc mockMvc;


    @Test
    @DisplayName("测试请求")
    void test1() throws Exception {
        log.info("测试开始");
        AzureParam azureParamRecord = new AzureParam();
        azureParamRecord.setTenantId("1");
        azureParamRecord.setClientId("2");
        azureParamRecord.setClientSecret("3");
        String jsonStr = JSONUtil.toJsonStr(azureParamRecord);
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/test/bug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr));
        perform.andExpect(status().isOk());
        perform.andDo(print());
    }
}