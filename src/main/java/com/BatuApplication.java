package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "com")	// enable @Controller, @Service @Repository 
public class BatuApplication { 
 
	public static void main(String[] args) {
		SpringApplication.run(BatuApplication.class, args);
		System.err.println("Up");
	}

}
