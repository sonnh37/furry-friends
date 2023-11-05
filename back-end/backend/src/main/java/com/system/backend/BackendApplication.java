package com.system.backend;

import com.system.backend.Entity.Role;
import com.system.backend.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication {
//	@Autowired
//	private RoleRepository repository;
////
//	@PostConstruct
//	public void initRoles(){
//		List<Role> roles = Stream.of(
//				new Role(null, "member"),
//				new Role(null, "staff"),
//				new Role(null, "admin")
//		).collect(Collectors.toList());
//		repository.saveAll(roles);
//	}
//	@PostConstruct
//	public void initUsers() {
//		List<User> users = Stream.of(
//				new User(null, new Role(1, "member"), "javatechie", "password", "javatechie@gmail.com"
//				,"first", "last", "098765432", "10/10", "1/1/2003", "nam"
//				),
//				new User(null, new Role(1, "member"), "user1", "pw1", "user1@gmail.com"
//						,"first", "last", "098765432", "10/10", "1/1/2003", "nam"
//				)
//		).collect(Collectors.toList());
//		repository.saveAll(users);
//	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
