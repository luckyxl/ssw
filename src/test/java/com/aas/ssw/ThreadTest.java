package com.aas.ssw;

import com.aas.ssw.business.service.KpiInfoService;
import com.aas.ssw.business.entity.KpiInfo;
import com.aas.ssw.common.util.ThreadExecutorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ThreadTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);

    @Resource
    private KpiInfoService kpiInfoService;

    @Test
    public void selectKpiInfo() {
        int id = 10;
        Future<KpiInfo> kpiInfoFuture = kpiInfoService.selectKpiInfoByIdAsync(id);
        kpiInfoService.selectKpiInfoById(id);
        FutureTask<KpiInfo> futureTask = new FutureTask<>(() -> kpiInfoService.selectKpiInfoByIdAsync2(id));
        ThreadExecutorUtil.concurrentExcute(new FutureTask[]{futureTask},new String[]{"task1"});
        LOGGER.info("查询成功");
    }
}
