package com.aas.ssw.business.service.impl;

import com.aas.ssw.business.service.KpiInfoService;
import com.aas.ssw.business.dao.one.KpiInfoDao;
import com.aas.ssw.business.entity.KpiInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@ConditionalOnProperty(name = "spring.jta.enabled")
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

    @Cacheable(value = "kpi",key = "'kpiId:' + #id", unless="#result == null")
    @Override
    public KpiInfo getById(Integer id) {
        return kpiInfoDao.selectByPrimaryKey(id);
    }
    @CachePut(value = "kpi",key = "'kpiId:' + #kpiInfo.id")
    @Override
    public KpiInfo updateById(KpiInfo kpiInfo) {
        kpiInfoDao.updateByPrimaryKeySelective(kpiInfo);
        return kpiInfoDao.selectByPrimaryKey(kpiInfo.getId());
    }
    @CacheEvict(value = "kpi",key = "'kpiId:' + #id")
    @Override
    public void logicDeleteById(Integer id) {
        KpiInfo kpiInfo = new KpiInfo();
        kpiInfo.setId(id);
        kpiInfo.setName("redisTest");
        //模拟逻辑删除
        kpiInfoDao.updateByPrimaryKeySelective(kpiInfo);
    }
}
