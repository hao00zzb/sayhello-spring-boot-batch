package com.suntomor.springbatch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EchoTasklet implements Tasklet, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(EchoTasklet.class);

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("StepContribution:{}", stepContribution.toString());
        logger.info("ChunkContext:{}", chunkContext.toString());
        logger.info("ApplicationName:{}", applicationContext);
        return RepeatStatus.FINISHED;
    }

}
