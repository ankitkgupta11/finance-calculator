package com.finapp.finance_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinanceCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceCalculatorApplication.class, args);
		System.out.println("Jai Siya Ram"); 
	}

}
