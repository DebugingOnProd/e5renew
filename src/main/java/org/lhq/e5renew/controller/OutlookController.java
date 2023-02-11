package org.lhq.e5renew.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.lhq.e5renew.entity.OutlookConfig;
import org.lhq.e5renew.service.MsApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("outlook")
@Tag(name = "api调用必要参数控制器", description = "保存配置")
public class OutlookController {

	private final MsApiService msApiService;

	public OutlookController(MsApiService msApiService) {
		this.msApiService = msApiService;
	}


	@PostMapping("save")
	@Operation(summary = "保存配置")
	public OutlookConfig saveOutlookConfig(@RequestBody OutlookConfig outlookConfig) {
		return msApiService.saveConfig(outlookConfig);
	}

}
