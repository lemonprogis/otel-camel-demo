package com.example;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DispatchRoute extends RouteBuilder {
    private final ServiceProperties serviceProperties;

    public DispatchRoute(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @Override
    public void configure() {
        from("direct:dispatcher").routeId("dispatcher-route")
                .log("incoming request, body = ${body}")
                .to("direct:sayHello");

        from("direct:sayHello").routeId("sayHello-route")
                .log("dispatching to /hello endpoint")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to(serviceProperties.getDownstreamEndpoint())
                .convertBodyTo(String.class);

    }

}
