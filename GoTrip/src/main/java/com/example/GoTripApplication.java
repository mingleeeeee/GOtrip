package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import com.example.storage.StorageProperties;

@EnableJdbcHttpSession
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GoTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoTripApplication.class, args);
	}
}
