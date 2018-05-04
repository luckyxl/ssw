package com.aas.ssw.common.component;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.Data;

@Data
public abstract class BaseElasticJob implements SimpleJob {
    private String cron;
    private int shardingTotalCount;
    private String shardingItemParameters;
}
