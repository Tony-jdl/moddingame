package it.moddingame.rest.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${enableSwagger}")
    private boolean enableSwagger;

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("corso-rest")
                .apiInfo(apiInfo())
		.enable(enableSwagger)
		.select()
                .apis(RequestHandlerSelectors.basePackage("it.moddingame.rest.controller")).paths(postPaths())
                .paths(postPaths())
                .build();
    }

    private Contact buildContact() {
        return new Contact("Moddingame", "http://www.moddingame.it", "");
    }


    private Predicate<String> postPaths() {
        return regex("/*.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("moddingame-rest Ms API").contact(buildContact())
                .description("API per il colloquio con il microservizio moddingame-rest")
                .termsOfServiceUrl("http://www.moddingame.it").version("1.0.0").build();
    }


    @Bean
    @ConditionalOnProperty(name = "disableSwaggerTryOut", havingValue = "true", matchIfMissing = false)
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, "none", "alpha", "schema",
                new String[]{}, false, true, 60000L);
    }
}

