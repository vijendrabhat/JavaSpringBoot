package com.vij.employee.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagement {

	//logging
		static final Logger logger  = LogManager.getLogger(EmployeeManagement.class.getName());
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("Entered EmployeeManagement");
		SpringApplication.run(EmployeeManagement.class, args);
	}

}
