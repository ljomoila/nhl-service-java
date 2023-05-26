package com.ljomoila.nhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NhlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhlApplication.class, args);
	}

}
