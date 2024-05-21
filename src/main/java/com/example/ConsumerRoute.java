package com.example;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.opentelemetry.OpenTelemetryTracer;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        OpenTelemetryTracer ott = new OpenTelemetryTracer();
        ott.init(this.getContext());

        from("activemq:queue:TEST_Q").routeId("activemq-consumer")
                .log(LoggingLevel.INFO, "Received \"${body}\" from ActiveMQ");

    }
}
