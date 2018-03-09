package com.aas.ssw.business.Service.impl;

import com.aas.ssw.business.Service.KpiInfoService;
import com.aas.ssw.business.dao.KpiInfoDao;
import com.aas.ssw.business.entity.KpiInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xl
 */
@Service("kpiInfoService")
public class KpiInfoServiceImpl implements KpiInfoService{
    @Resource
    private KpiInfoDao kpiInfoDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertKpiInfo(KpiInfo kpiInfo) {
        kpiInfoDao.insertSelective(kpiInfo);
        int i = 3/0;
    }
}
