package com.spring.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.reserva.services.DbService;

@SpringBootApplication
public class ReservaApplication implements CommandLineRunner {

	@Autowired
	private DbService dbService;
	
	public static void main(String[] args) {
		SpringApplication.run(ReservaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.dbService.dbInstance();
	}

}
