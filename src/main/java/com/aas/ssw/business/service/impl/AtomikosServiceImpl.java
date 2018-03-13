package com.aas.ssw.business.service.impl;

import com.aas.ssw.business.dao.one.KpiInfoDao;
import com.aas.ssw.business.dao.two.AekoTulDao;
import com.aas.ssw.business.entity.AekoTul;
import com.aas.ssw.business.entity.KpiInfo;
import com.aas.ssw.business.service.AtomikosService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("atomikosService")
@ConditionalOnProperty(name = "spring.jta.enabled")
public class AtomikosServiceImpl implements AtomikosService {
    @Resource
    private KpiInfoDao kpiInfoDao;
    @Resource
    private AekoTulDao aekoTulDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test() {
        AekoTul aekoTul = new AekoTul();
        aekoTul.setTUL_ID(1917L);
        aekoTul.setLNR(28L);
        aekoTul.setDESCRIPTION("TEST");
        aekoTulDao.updateByPrimaryKeySelective(aekoTul);
        KpiInfo kpiInfo = new KpiInfo();
        kpiInfo.setId(19);
        kpiInfo.setDescription("TEST");
        kpiInfoDao.updateByPrimaryKeySelective(kpiInfo);

    }
}
