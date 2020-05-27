package com.liada.microcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.liada.service","com.liada.microcloud"})
@EnableFeignClients(basePackages = { "com.liada.service" })
//@RibbonClient(name="ribbonClient",configuration=MyLoadBalanceConfig.class)//自定义负载均衡策略
public class ApplicationHystrixFeign {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationHystrixFeign.class, args);
	}
}
