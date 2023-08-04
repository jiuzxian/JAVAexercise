package com.example.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//开启事务支持
@SpringBootApplication
public class SpringtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringtestApplication.class, args);
	}

}
