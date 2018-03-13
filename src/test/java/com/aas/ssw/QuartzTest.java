package com.aas.ssw;

import com.aas.ssw.common.component.ScheduleJob;
import com.aas.ssw.common.util.QuartzUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class QuartzTest {


    @Test
    public void addJobTest() throws InterruptedException {
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
        Thread.sleep(20 * 1000);
    }

    @Test
    public void updateJobTest() throws InterruptedException {
        ScheduleJob job = new ScheduleJob();
        job.setJobName("ssww");
        job.setJobGroup("ssww");
        job.setCronExpression("*/10 * * * * ?");
        job.setTriggerName("ssww");
        job.setTriggerGroup("ssww");
        job.setIsSpringBean("1");
        job.setTargetObject("quartzJob");
        job.setTargetMethod("sayHi");
        job.setConcurrent("0");
        QuartzUtil.addJob(job);
        Thread.sleep(20 * 1000);
        job.setCronExpression("*/1 * * * * ?");
        QuartzUtil.modifyJobTime(job);
        Thread.sleep(15 * 1000);
    }

    @Test
    public void removeJobTest() throws InterruptedException {
        ScheduleJob job = new ScheduleJob();
        job.setJobName("ssww");
        job.setJobGroup("ssww");
        job.setCronExpression("*/2 * * * * ?");
        job.setTriggerName("ssww");
        job.setTriggerGroup("ssww");
        job.setIsSpringBean("1");
        job.setTargetObject("quartzJob");
        job.setTargetMethod("sayHi");
        job.setConcurrent("0");
        QuartzUtil.addJob(job);
        Thread.sleep(20 * 1000);
        QuartzUtil.removeJob(job);
        Thread.sleep(10 * 1000);
    }


}
