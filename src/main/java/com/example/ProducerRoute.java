package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.opentelemetry.OpenTelemetryTracer;
import org.springframework.stereotype.Component;

@Component
public class ProducerRoute extends RouteBuilder {

    private final ServiceProperties serviceProperties;
    private final WeatherLocation weatherLocation;

    public ProducerRoute(ServiceProperties serviceProperties, WeatherLocation weatherLocation) {
        this.serviceProperties = serviceProperties;
        this.weatherLocation = weatherLocation;
    }

    @Override
    public void configure() {
        OpenTelemetryTracer ott = new OpenTelemetryTracer();
        ott.init(this.getContext());

        from("timer:hello?period={{timer.period}}")
                .routeId("activemq-producer")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to(weatherUrl())
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "Sending ${body} to ActiveMQ")
                .to("activemq:queue:TEST_Q");
    }

    private String weatherUrl() {
        return serviceProperties.getDownstreamEndpoint2()+"?lat=" + weatherLocation.getLatitude() + "&lng=" + weatherLocation.getLongitude();
    }

}
