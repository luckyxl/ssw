package com.aas.ssw;

import com.aas.ssw.business.entity.KpiInfo;
import com.aas.ssw.business.service.KpiInfoService;
import com.aas.ssw.common.component.ScheduleJob;
import com.aas.ssw.common.util.QuartzUtil;
import com.aas.ssw.common.util.ThreadExecutorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class QuartzTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTest.class);

    @Test
    public void addJobTest(){
        ScheduleJob job = new ScheduleJob();
        job.setJobName("ssww");
        job.setJobGroup("ssww");
        job.setCronExpression("*/5 * * * * ?");
        job.setTriggerName("ssww");
        job.setTriggerGroup("ssww");
        job.setIsSpringBean("1");
        job.setTargetObject("quartzJob");
        job.setTargetMethod("sayHi");
        job.setConcurrent("0");
        QuartzUtil.addJob(job);
        while(true){}
    }
    @Test
    public void updateJobTest(){
        ScheduleJob job = new ScheduleJob();
        job.setJobName("ssw");
        job.setJobGroup("ssw");
        job.setCronExpression("*/3 * * * * ?");
        job.setTriggerName("ssw");
        job.setTriggerGroup("ssw");
        job.setIsSpringBean("1");
        job.setTargetObject("quartzJob");
        job.setTargetMethod("sayHello");
        job.setConcurrent("0");
        QuartzUtil.modifyJobTime(job);
        while(true){}
    }
    @Test
    public void removeJobTest(){
        ScheduleJob job = new ScheduleJob();
        job.setJobName("ssww");
        job.setJobGroup("ssww");
        job.setTriggerName("ssww");
        job.setTriggerGroup("ssww");
        QuartzUtil.removeJob(job);
    }



}
