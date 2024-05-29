package com.example;

import org.apache.camel.opentelemetry.starter.CamelOpenTelemetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CamelOpenTelemetry
@SpringBootApplication
public class OtelCamelDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtelCamelDemoApplication.class, args);
	}

}
