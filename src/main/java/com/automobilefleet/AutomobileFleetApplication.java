package com.automobilefleet;

import com.automobilefleet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AutomobileFleetApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(AutomobileFleetApplication.class, args);
	}

	@Override
	public void run(String... args) {
//		var fake = Faker.instance();
//
//		repository.save(User.builder()
//				.username(("cleu"))
//						.password(encoder.encode("123"))
//						.role(Role.ROLE_ADMIN)
//						.email("feufumafriceu-5535@yopmail.com")
//				.build());
	}
}
