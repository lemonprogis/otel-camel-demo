package com.example;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("activemq:queue:TEST_Q").routeId("activemq-consumer")
                .log(LoggingLevel.INFO, "Received \"${body}\" from ActiveMQ");

    }
}
