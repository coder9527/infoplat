package com.iyue.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 服务注册中心
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	
    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    	//PropertyPlaceholder.getProperty("sql.name");
    	System.out.println("ceshi:"+context.getEnvironment().getProperty("eureka.instance.hostname"));
    }
}
