package org.lhq.e5renew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.lhq.e5renew.entity.Cron;
import org.lhq.e5renew.service.impl.SpringDynamicCornTask;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("cron")
@Tag(name = "动态修改Cron", description = "动态修改Cron")
public class CronController {
    @Resource
    private SpringDynamicCornTask springDynamicCornTask;

    @PostMapping("/setCron")
    @Operation(summary = "设置Cron")
    public String setCron(@RequestBody Cron cron){
        springDynamicCornTask.setCron(cron.getCron());
        return "success";
    }
    @GetMapping("/getCron")
    @Operation(summary = "查询Cron")
    public String getCron(){
        String cron = springDynamicCornTask.getCron();
        return "cron:"+cron;
    }
}
