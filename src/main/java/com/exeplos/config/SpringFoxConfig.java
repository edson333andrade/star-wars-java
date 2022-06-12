package com.exeplos.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.swagger.models.Scheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.swagger.models.auth.In.HEADER;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Configuration
@EnableSwagger2
public class SpringFoxConfig extends WebMvcConfigurationSupport {
    @Value("${swagger.api.version}")
    private String version;

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(ResponseEntity.class, Void.class)
                .directModelSubstitute(Object.class, Void.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.exeplos.controllers"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .consumes(Sets.newHashSet(APPLICATION_JSON_UTF8_VALUE))
                .produces(Sets.newHashSet(APPLICATION_JSON_UTF8_VALUE))
                .protocols(Sets.newHashSet(Scheme.HTTP.name()))
                .apiInfo(metaData())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .globalOperationParameters(globalParameters());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("API Star Wars ")
                .description("")
                .termsOfServiceUrl("")
                .version(version).build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiKey apiKey() {
        return new ApiKey("jwt", HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(authDefinition())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> authDefinition() {
        final var scopes = new AuthorizationScope[0];
        return Lists.newArrayList(
                SecurityReference.builder()
                        .reference("jwt")
                        .scopes(scopes)
                        .build()
        );
    }

    private List<Parameter> globalParameters() {
        final var parameters = new ArrayList<Parameter>();
        parameters.add(new ParameterBuilder()
                .name("x-transaction-id")
                .description(UUID.randomUUID().toString())
                .modelRef(new ModelRef("string"))
                .parameterType(HEADER.name())
                .required(true)
                .build());

        return parameters;
    }

}
