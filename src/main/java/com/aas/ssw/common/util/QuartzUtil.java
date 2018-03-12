package com.aas.ssw.common.util;

import com.aas.ssw.common.component.Constant;
import com.aas.ssw.common.component.ScheduleJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzUtil implements ApplicationContextAware{

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzUtil.class);

    private static ApplicationContext applicationContext;
    private static Scheduler scheduler;

    /**
     * @Description: 添加一个定时任务
     *
     * @param job 任务名
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String addJob(ScheduleJob job) {
        try {
            if(job == null){
                LOGGER.warn("job为null！");
                return Constant.FAIL;
            }
            if(scheduler.getJobDetail(JobKey.jobKey(job.getJobName(), job.getJobGroup())) == null){
                LOGGER.warn("不能添加定时任务，因为jobName："+ job.getJobName() + ",jobGroupName:" + job.getJobGroup() +"已经存在");
                return Constant.FAIL;
            }
            MethodInvokingJobDetailFactoryBean methodInvJobDetail = new MethodInvokingJobDetailFactoryBean();
            methodInvJobDetail.setName(job.getJobName());
            methodInvJobDetail.setGroup(job.getJobGroup());
            if (job.getIsSpringBean().equals("1")) {// 是Spring中定义的Bean
                methodInvJobDetail
                        .setTargetObject(applicationContext.getBean(job.getTargetObject()));
            } else {
                // 不是
                methodInvJobDetail.setTargetObject(Class.forName(job.getClazz()).newInstance());
            }
            // 设置任务方法
            methodInvJobDetail.setTargetMethod(job.getTargetMethod());
            // 将管理Job类提交到计划管理类
            methodInvJobDetail.afterPropertiesSet();
            // 并发设置
            methodInvJobDetail.setConcurrent(job.getConcurrent().equals("1") ? true : false);
            // 动态
            JobDetail jobDetail = methodInvJobDetail.getObject();
//            jobDetail.getJobDataMap().put("scheduleJob", job);

            // 任务名，任务组，任务执行类
//            JobDetail jobDetail= JobBuilder.newJob(methodInvJobDetail.).withIdentity(jobName, jobGroupName).build();
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(job.getTriggerName(), job.getJobGroup());
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
            return Constant.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("添加定时任务异常！",e);
            return Constant.ERROR;
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     *
     * @param job
     */
    public static String modifyJobTime(ScheduleJob job) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                LOGGER.warn("不能修改定时任务，因为triggerName：" + job.getTriggerName() + ",triggerGroupName:" + job.getJobGroup() + "不存在！");
                return Constant.FAIL;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(job.getCronExpression())) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(job.getTriggerName(), job.getTriggerGroup());
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
            return Constant.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("修改定时任务异常！",e);
            return Constant.ERROR;
        }
    }

    /**
     * @Description: 移除一个任务
     *
     * @param job
     */
    public static String removeJob(ScheduleJob job) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroup());
            if(triggerKey != null){
                // 停止触发器
                scheduler.pauseTrigger(triggerKey);
                // 移除触发器
                scheduler.unscheduleJob(triggerKey);
                // 删除任务
                scheduler.deleteJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
            }
            return Constant.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("删除定时任务异常！",e);
            return Constant.ERROR;
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public static void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







    @Resource(name = "scheduler")
    public void setScheduler(Scheduler scheduler) {
        QuartzUtil.scheduler = scheduler;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        QuartzUtil.applicationContext = applicationContext;
    }
}
