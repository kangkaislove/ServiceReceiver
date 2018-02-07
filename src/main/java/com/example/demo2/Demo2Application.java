package com.example.demo2;

import com.example.demo2.transmission.JsDataToKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);

		//启动上传数据至kafka的线程
		new Thread(new JsDataToKafka()).start();
	}
}
