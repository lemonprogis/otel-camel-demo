package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestOtelCamelDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(OtelCamelDemoApplication::main).with(TestOtelCamelDemoApplication.class).run(args);
	}

}
