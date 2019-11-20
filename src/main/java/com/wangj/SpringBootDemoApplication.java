package com.wangj;

import com.wangj.service.threadPool.ThreadPoolService;
import com.wangj.util.LogCostInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class SpringBootDemoApplication implements WebMvcConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);

    @Autowired
    private LogCostInterceptor logCostInterceptor;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootDemoApplication.class, args);
        logger.info("server.port = " + ctx.getEnvironment().getProperty("server.port"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logCostInterceptor).addPathPatterns("/**");
    }
}
