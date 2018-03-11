package com.aas.ssw;

import com.aas.ssw.business.Service.KpiInfoService;
import com.aas.ssw.business.entity.KpiInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionalTest.class);

    @Resource
    private KpiInfoService kpiInfoService;

    @Test
    public void insertKpiInfo() {
        KpiInfo kpiInfo = new KpiInfo();
        kpiInfo.setFrequency("0");
        kpiInfo.setLevel("0");
        kpiInfo.setTarget(0.5);
        kpiInfoService.insertKpiInfo(kpiInfo);
        LOGGER.info("插入成功");
    }
}
