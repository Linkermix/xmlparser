package com.example.xmlparser.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Autowired
    BuildProperties buildProperties;

    @Value("${swagger.server}")
    String swaggerServer;

    @Bean
    public OpenAPI customOpen() {
        return new OpenAPI().addServersItem(new Server().url(swaggerServer))
                .info(new Info().title("Xml parser").version("V" + buildProperties.getVersion())
                .license(new License().name("Example").url("https://")));
    }
}
