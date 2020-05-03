package com.lokesh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import com.lokesh.serviceImpl.UserServiceImpl;

/**
 * @author lokesh
 * CommandLineRunner is for calling  non static method from static method means from main method which is static method
 *
 */
@SpringBootApplication
@EnableRetry
public class SpringBootMySqlApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMySqlApplication.class, args);
	}

	
	/**
	 * overridden method of CommandLineRunner
	 */
	@Override
	public void run(String... args) throws Exception {

		userService.fetchStudentJson();

	}


}
