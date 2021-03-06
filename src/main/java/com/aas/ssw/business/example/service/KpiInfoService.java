package com.aas.ssw.business.example.service;

import com.aas.ssw.business.example.entity.KpiInfo;

import java.util.concurrent.Future;

/**
 * @author xl
 */
public interface KpiInfoService {

    void insertKpiInfo(KpiInfo kpiInfo);

    Future<KpiInfo> selectKpiInfoByIdAsync(Integer id);

    KpiInfo selectKpiInfoById(Integer id);

    KpiInfo selectKpiInfoByIdAsync2(Integer id);

    KpiInfo getById(Integer id);

    KpiInfo updateById(KpiInfo kpiInfo);

    void logicDeleteById(Integer id);

    String updateById2(KpiInfo kpiInfo);
}
