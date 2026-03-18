package org.example.parking_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.parking_management_system.mapper")
public class ParkingManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingManagementSystemApplication.class, args);
	}

}