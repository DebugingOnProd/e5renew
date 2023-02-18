package org.lhq.e5renew.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("request_log")
public class RequestLog {
    private Long id;
    private String apiName;
    private LocalDateTime requestTime;

    private boolean execResult;
}
