package com.kafka.kafkasession;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSessionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSessionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Producer1 producer1 = new Producer1();
		producer1.main(args);
	}

}
