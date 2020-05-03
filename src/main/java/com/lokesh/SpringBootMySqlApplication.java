package com.lokesh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lokesh.serviceImpl.UserServiceImpl;

@SpringBootApplication
public class SpringBootMySqlApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMySqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userService.fetchStudentJson();

	}


}
