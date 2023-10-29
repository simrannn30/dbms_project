package com.simran.demo;

import com.simran.demo.config.SecurityConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//@SpringBootApplication
//@ComponentScan(basePackages = { "com.simran.demo.controller", "com.simran.demo.model", "com.simran.demo.repository",
//		"com.simran.demo.api", "com.simran.demo.dao", "com.simran.demo.services", "com.simran.demo.service","com.simran.demo.config"})
//@EnableJdbcRepositories(basePackages = { "com.simran.demo.repository" })
//@EntityScan(basePackages = { "com.simran.demo.model" })
@SpringBootApplication
public class FoodDistributionManagementApplication{
	public static void main(String[] args) {
		SpringApplication.run(FoodDistributionManagementApplication.class, args);
	}
//	@Override
//	public void run(String... args) throws Exception{
//		SpringApplication.run(SecurityConfiguration.class, args);
//	}
}
