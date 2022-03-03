package com.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * It can be disabled with:
 * --doc.enabled=false
 * e.g:
 * java -jar target\demo-0.0.1-SNAPSHOT.jar --doc.enabled=false
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(
        name = "doc.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class DocConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
