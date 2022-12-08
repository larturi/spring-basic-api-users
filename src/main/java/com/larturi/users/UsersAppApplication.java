package com.larturi.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class UsersAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersAppApplication.class, args);
	}

}
