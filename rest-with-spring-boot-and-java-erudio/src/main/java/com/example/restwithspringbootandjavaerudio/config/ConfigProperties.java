package com.example.restwithspringbootandjavaerudio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class ConfigProperties {

    private String urlExample;
}
