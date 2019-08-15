package com.liada.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.liada.springboot.mybatis.dao"})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
