package com.aas.ssw.common.component;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = -8256746547312736636L;
    /**
     * 任务ID
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 0禁用 1启用 2删除
     */
    private String jobStatus;
    /**
     * 任务运行时间表达式
     */
    private String cronExpression;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 任务类
     */
    private String targetObject;
    /**
     * 任务方法
     */
    private String targetMethod;
    /**
     * 是否是Spring中定义的Bean
     */
    private String isSpringBean;
    /**
     * 如果isSpringBean = 0需要设置全类名,测试CLAZZ字段需要配置
     */
    private String clazz;
    /**
     * 是否并发 0禁用 1启用
     */
    private String concurrent;
    /**
     * triggerName
     */
    private String triggerName;
    /**
     * triggerGroup
     */
    private String triggerGroup;
}
