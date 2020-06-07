package com.janiszewski.MSSQLConnection.common;

import com.janiszewski.MSSQLConnection.MssqlConnectionApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//to run Spring Boot applications from a classical WAR archive
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MssqlConnectionApplication.class);
	}

}
