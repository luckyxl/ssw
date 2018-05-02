package com.aas.ssw.config;

import com.aas.ssw.business.example.ElasticJob;
import com.aas.ssw.common.component.BaseElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(value = "elasticjob.enables")
public class StockJobConfig {
    @Resource
    private ZookeeperRegistryCenter regCenter;


    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler() {
        BaseElasticJob simpleJob = new ElasticJob();
        simpleJob.setCron("0/10 * * * * ?");
        simpleJob.setShardingTotalCount(2);
        simpleJob.setShardingItemParameters("0=aas0,1=aas1");
        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(),
                simpleJob.getCron(), simpleJob.getShardingTotalCount(), simpleJob.getShardingItemParameters()));
    }


    /**
     * @Description 任务配置类
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {
        return LiteJobConfiguration
                .newBuilder(
                        new SimpleJobConfiguration(
                                JobCoreConfiguration.newBuilder(
                                        jobClass.getName(), cron, shardingTotalCount)
                                        .shardingItemParameters(shardingItemParameters)
                                        .build()
                                , jobClass.getCanonicalName()
                        )
                )
                .overwrite(true)
                .build();
    }
}
