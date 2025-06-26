package com.learnwithiftekhar.spring_jwt_demo;

import com.learnwithiftekhar.spring_jwt_demo.model.Role;
import com.learnwithiftekhar.spring_jwt_demo.model.User;
import com.learnwithiftekhar.spring_jwt_demo.ropository.RoleRepository;
import com.learnwithiftekhar.spring_jwt_demo.ropository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringJwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		return args -> {

			Role adminRole = new Role("ADMIN");
			Role userRole = new Role("USER");

			roleRepository.saveAll(List.of(adminRole, userRole));

			User admin = new User();
			admin.setUsername("admin");
			admin.addRole(adminRole);
			admin.setPassword(passwordEncoder.encode("admin"));

			User user = new User();
			user.setUsername("user");
			user.addRole(userRole);
			user.setPassword(passwordEncoder.encode("user"));

			repository.saveAll(List.of(admin, user));
		};
	}
}
