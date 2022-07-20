package edu.poly.assigmentsof3021;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import edu.poly.assigmentsof3021.config.StorageProperties;
import edu.poly.assigmentsof3021.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class Assigmentsof3021Application {

	public static void main(String[] args) {
		SpringApplication.run(Assigmentsof3021Application.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args ->{
			storageService.init();
		});
	}
}
