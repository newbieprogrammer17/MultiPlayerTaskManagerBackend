package com.b902.watersupply;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WaterSupplyApplication implements CommandLineRunner{
	private final WaterRepository waterRepository;

	public WaterSupplyApplication(WaterRepository waterRepository) {
		this.waterRepository = waterRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WaterSupplyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<WaterUsers> users = waterRepository.findCurrentCycleUsers();

		users.forEach(System.out::println);
	}
}
