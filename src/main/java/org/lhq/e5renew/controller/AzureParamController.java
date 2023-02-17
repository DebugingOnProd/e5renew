package org.lhq.e5renew.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.lhq.e5renew.service.AzureParamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/azure")
public class AzureParamController {
    private final AzureParamService azureParamService;

    public AzureParamController(AzureParamService azureParamService) {
        this.azureParamService = azureParamService;
    }

    @PostMapping("/save")
    public void saveAzureParam(@RequestBody AzureParamRecord azureParamRecord){
        azureParamService.save(azureParamRecord);
    }


    @GetMapping("page")
    public IPage<AzureParam> selectPage(Integer pageNum,Integer pageSize){
       return azureParamService.selectPage(pageNum, pageSize);
    }
}
