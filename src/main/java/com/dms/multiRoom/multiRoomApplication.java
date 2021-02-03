package com.dms.multiRoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dms.chat", "com.dms.controller"})
public class multiRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(multiRoomApplication.class, args);
	}

	
}
