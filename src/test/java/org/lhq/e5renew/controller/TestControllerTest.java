package org.lhq.e5renew.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class TestControllerTest {

    @Resource
    private MockMvc mockMvc;


    @Test
    void test1() throws Exception {
        log.info("测试开始");
        mockMvc.perform(MockMvcRequestBuilders.post("/test/bug")).andDo(print());
    }
}