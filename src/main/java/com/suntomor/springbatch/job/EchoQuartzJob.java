package com.suntomor.springbatch.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;

public class EchoQuartzJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(EchoQuartzJob.class);
    private static final String JOB_NAME = "jobName";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info(Thread.currentThread().toString());
        JobDataMap jobDataMap = context.getMergedJobDataMap();

        String jobName = (String) jobDataMap.get(JOB_NAME);
        try {
            final SchedulerContext schedulerContext = context.getScheduler().getContext();
            final ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContextKey");

            JobLocator jobLocator = applicationContext.getBean(JobLocator.class);
            JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
            JobParametersBuilder builder = new JobParametersBuilder();
            builder.addLong("currentTimeMillis", System.currentTimeMillis());

            jobLauncher.run(jobLocator.getJob(jobName), builder.toJobParameters());
        } catch (NoSuchJobException | JobInstanceAlreadyCompleteException | JobRestartException | JobExecutionAlreadyRunningException | JobParametersInvalidException | SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
