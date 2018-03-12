package com.aas.ssw;

import com.aas.ssw.business.Service.KpiInfoService;
import com.aas.ssw.business.entity.KpiInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RedisTest {

    @Resource
    private KpiInfoService kpiInfoService;


    @Test
    public void cacheableTest(){
        KpiInfo kpiInfo = kpiInfoService.getById(10);
        System.out.println("owner:" + kpiInfo.getOwner());
    }
    @Test
    public void cachePutTest(){
        KpiInfo kpiInfo = new KpiInfo();
        kpiInfo.setId(10);
        kpiInfo.setOwner("aas");
        kpiInfoService.updateById(kpiInfo);
    }
    @Test
    public void cacheEvictTest(){
        kpiInfoService.logicDeleteById(10);
    }
}
