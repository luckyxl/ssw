package com.aas.ssw;

import com.aas.ssw.business.entity.KpiInfo;
import com.aas.ssw.business.service.AtomikosService;
import com.aas.ssw.business.service.KpiInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AtomikosTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtomikosTest.class);

    @Autowired(required = false)
    private KpiInfoService kpiInfoService;
    @Autowired(required = false)
    private AtomikosService atomikosService;

    @Test
    public void insertKpiInfoTest() {
        KpiInfo kpiInfo = new KpiInfo();
        kpiInfo.setFrequency("0");
        kpiInfo.setLevel("0");
        kpiInfo.setTarget(0.5);
        kpiInfoService.insertKpiInfo(kpiInfo);
        LOGGER.info("插入成功");
    }
    @Test
    public void atomikosTest() {
        atomikosService.test();
    }
}
