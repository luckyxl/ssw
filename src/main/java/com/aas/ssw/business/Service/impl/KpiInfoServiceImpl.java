package com.aas.ssw.business.Service.impl;

import com.aas.ssw.business.Service.KpiInfoService;
import com.aas.ssw.business.dao.KpiInfoDao;
import com.aas.ssw.business.entity.KpiInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author xl
 */
@Service("kpiInfoService")
public class KpiInfoServiceImpl implements KpiInfoService{

    private static final Logger LOGGER = LoggerFactory.getLogger(KpiInfoServiceImpl.class);
    @Resource
    private KpiInfoDao kpiInfoDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertKpiInfo(KpiInfo kpiInfo) {
        kpiInfoDao.insertSelective(kpiInfo);
        int i = 3/0;
    }

    @Async
    @Override
    public Future<KpiInfo> selectKpiInfoByIdAsync(Integer id) {
        LOGGER.info("这是异步查询");
        return new AsyncResult<>(kpiInfoDao.selectByPrimaryKey(id));
    }

    @Override
    public KpiInfo selectKpiInfoById(Integer id) {
        LOGGER.info("这是同步查询");
        return kpiInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public KpiInfo selectKpiInfoByIdAsync2(Integer id) {
        LOGGER.info("如果使用线程池执行这个方法，那么这就是异步查询");
        return kpiInfoDao.selectByPrimaryKey(id);
    }
}
