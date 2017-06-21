package com.suntomor.bootstrap;

import com.suntomor.springbatch.job.EchoQuartzJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@ImportAutoConfiguration(value = TaskletConfiguration.class)
public class Quartz2Configuration {

    @Bean
    public JobDetailFactoryBean echoJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(EchoQuartzJob.class);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "echoBatchJob");
        jobDetailFactoryBean.setJobDataMap(jobDataMap);

        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean echoJobTrigger(JobDetail echoJobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
        cronTriggerFactoryBean.setJobDetail(echoJobDetail);
        return cronTriggerFactoryBean;
    }

    @Bean
    @Lazy
    public SchedulerFactoryBean schedulerFactoryBean(Trigger echoJobTrigger) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        schedulerFactoryBean.setOverwriteExistingJobs(false);
        schedulerFactoryBean.setAutoStartup(true);

        schedulerFactoryBean.setTriggers(echoJobTrigger);
        return schedulerFactoryBean;
    }

}
