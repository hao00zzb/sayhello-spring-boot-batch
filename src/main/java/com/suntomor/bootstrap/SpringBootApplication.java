package com.suntomor.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        ProjectInfoAutoConfiguration.class,
        WebClientAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        ConfigurationPropertiesAutoConfiguration.class
})
@ComponentScan(basePackages = "com.suntomor.springbatch")
@EnableBatchProcessing
@ImportAutoConfiguration(classes = {DataSourceConfiguration.class, Quartz2Configuration.class})
public class SpringBootApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(SpringBootApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .registerShutdownHook(true)
                .web(false)
                .application();
        springApplication.run(args);
        logger.info("spring batch starter.....");
    }

}
