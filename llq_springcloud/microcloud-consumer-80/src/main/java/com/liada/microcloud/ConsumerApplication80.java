package com.liada.microcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.liada.commons.config.MyLoadBalanceConfig;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="ribbonClient",configuration=MyLoadBalanceConfig.class)//自定义负载均衡策略
public class ConsumerApplication80 {
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication80.class, args);
	}
}
