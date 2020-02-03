package com.kafka.kafkasessionconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSessionConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSessionConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Consumer1 consumer1 = new Consumer1();
		consumer1.main(args);
	}
}
