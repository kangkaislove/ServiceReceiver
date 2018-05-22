package com.kk.receiver;

import com.kk.receiver.transmission.DataToKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceReceiverApplication.class, args);
	}
}
