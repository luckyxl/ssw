package com.aas.ssw.business.controller;

import com.aas.ssw.business.Service.KpiInfoService;
import com.aas.ssw.business.entity.KpiInfo;
import com.aas.ssw.common.component.Constant;
import com.aas.ssw.common.component.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author xl
 */
@Controller
@RequestMapping("/kpiInfo")
public class KpiInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiInfoController.class);
    @Resource
    private KpiInfoService kpiInfoService;

    @PostMapping("/insertKpiInfo")
    @ResponseBody
    public Result insertKpiInfo(KpiInfo kpiInfo) {
        try {
            kpiInfo.setFrequency("0");
            kpiInfo.setLevel("0");
            kpiInfo.setTarget(0.5);
            kpiInfoService.insertKpiInfo(kpiInfo);
            return Result.getInfo(Constant.SUCCESS, "插入成功", null);
        } catch (Exception e) {
            LOGGER.error("插入数据出错", e);
            return Result.getInfo(Constant.ERROR, "插入失败", null);
        }
    }
}
