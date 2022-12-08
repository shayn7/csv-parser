package com.naamad.csvparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CsvParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvParserApplication.class, args);
	}

}
