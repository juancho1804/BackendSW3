package com.unicauca.microservice_asginatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

 // Habilita Feign para detectar los clientes
 @EnableFeignClients
 @SpringBootApplication
public class MicroserviceAsginaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAsginaturaApplication.class, args);
	}

}
