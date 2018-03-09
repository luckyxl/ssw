package com.aas.ssw.business.controller;

import com.aas.ssw.business.Service.KpiInfoService;
import com.aas.ssw.business.entity.KpiInfo;
import com.aas.ssw.common.component.Constant;
import com.aas.ssw.common.component.Result;
import com.aas.ssw.common.util.ThreadExecutorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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
            return Result.getInfo(Constant.SUCCESS, "插入成功", null,null);
        } catch (Exception e) {
            LOGGER.error("插入数据出错", e);
            return Result.getInfo(Constant.ERROR, "插入失败", null,null);
        }
    }
    @GetMapping("/selectKpiInfo")
    @ResponseBody
    public Result selectKpiInfo(Integer id) {
        try {
            Future<KpiInfo> kpiInfoFuture = kpiInfoService.selectKpiInfoByIdAsync(id);
            kpiInfoService.selectKpiInfoById(id);
            FutureTask<KpiInfo> futureTask = new FutureTask<>(() -> kpiInfoService.selectKpiInfoByIdAsync2(id));
            ThreadExecutorUtil.concurrentExcute(new FutureTask[]{futureTask},new String[]{"task1"});
            LOGGER.info("查询成功");
            return Result.getInfo(Constant.SUCCESS, "查询成功", kpiInfoFuture.get(),null);
        } catch (Exception e) {
            LOGGER.error("查询数据出错", e);
            return Result.getInfo(Constant.ERROR, "查询失败", null,null);
        }
    }
}
