package com.janiszewski.MSSQLConnection;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j //lombok
@AllArgsConstructor //lombok
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class}) //disable WhileLabel eror page
public class MssqlConnectionApplication {


	public static void main(String[] args) {
		SpringApplication.run(MssqlConnectionApplication.class, args);
	}
}
