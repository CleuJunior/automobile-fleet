package com.automobilefleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AutomobileFleetApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomobileFleetApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
