package com.huzq.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ServiceHiApplication
 *
 * @author JackHu
 * @email 790327374@qq.com
 * @date 2019/3/28 16:06
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication {
    public static void main(String[] args){
         SpringApplication.run(ServiceHiApplication.class);
    }
    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi/{name}")
    public String Hi(@PathVariable(value = "name") String name,
                     @RequestParam(value = "message",required = false) String message){
        return port+"你好"+name;
    }
}
