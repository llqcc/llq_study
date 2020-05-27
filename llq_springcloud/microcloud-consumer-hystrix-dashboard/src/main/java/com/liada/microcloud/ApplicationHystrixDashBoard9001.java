package com.liada.microcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class ApplicationHystrixDashBoard9001 {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationHystrixDashBoard9001.class, args);
	}
}
