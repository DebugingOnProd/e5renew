package org.lhq.e5renew.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Lazy(false)
@Component
@EnableScheduling
@Slf4j
public class SpringDynamicCornTask implements SchedulingConfigurer {

    private static final String DEFAULT_CRON = "0/5 * * * * ?";
    private String cron = DEFAULT_CRON;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            log.info("真正的任务执行逻辑。。。"+"当前cron表达式："+getCron());
        }, triggerContext -> {
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext).toInstant();
        });
    }
    public void setCron(String cron) {
        System.out.println("原来的cron："+this.cron+"更新后的cron："+cron);
        this.cron = cron;
    }
    public String getCron() {
        return this.cron;
    }
}
