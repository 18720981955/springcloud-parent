package com.huzq.springcloud;

import com.huzq.springcloud.util.ContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * EurekaServerApplication
 *
 * @author JackHu
 * @email 790327374@qq.com
 * @date 2019/3/28 14:52
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    private static Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);
    public static void main(String[] args) throws FileNotFoundException {
        File logFile = new File("logs/log.log");
        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdir();
        }
        System.setErr(new PrintStream(new FileOutputStream(logFile, true)));
        SpringApplication application = new SpringApplication(EurekaServerApplication.class);
        application.addListeners(new ApplicationListener<ApplicationPreparedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationPreparedEvent event) {
                ContextUtils.setApplicationContext(event.getApplicationContext());
            }
        });
        application.run(args);
    }}
