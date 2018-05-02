package com.aas.ssw.common.component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.Data;

@Data
public class BaseElasticJob implements SimpleJob {
    private String cron;
    private int shardingTotalCount;
    private String shardingItemParameters;
    @Override
    public void execute(ShardingContext shardingContext) {

    }
}
