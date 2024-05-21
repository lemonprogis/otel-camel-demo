package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.opentelemetry.OpenTelemetryTracer;
import org.springframework.stereotype.Component;

@Component
public class ProducerRoute extends RouteBuilder {

    private final ServiceProperties serviceProperties;

    public ProducerRoute(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @Override
    public void configure() {
        OpenTelemetryTracer ott = new OpenTelemetryTracer();
        ott.init(this.getContext());

        from("timer:hello?period={{timer.period}}")
                .routeId("activemq-producer")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to(serviceProperties.getDownstreamEndpoint2()+"?lat=40.7517699&lng=-74.0526467") // just going to check empire state building weather.
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "Sending ${body} to ActiveMQ")
                .to("activemq:queue:TEST_Q");
    }

}
