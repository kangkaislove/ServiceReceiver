package com.kk.receiver;

import com.kk.receiver.transmission.JsDataToKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceReceiverApplication.class, args);

		//启动上传数据至kafka的线程
		new Thread(new JsDataToKafka()).start();
	}
}
