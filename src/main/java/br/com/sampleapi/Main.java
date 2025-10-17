package br.com.sampleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = JmsAutoConfiguration.class)
@EnableFeignClients
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
