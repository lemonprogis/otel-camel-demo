package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "srv")
@Data
public class ServiceProperties {
    private String downstreamEndpoint;
    private String downstreamEndpoint2;
}
