package com.grapheople.comarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@Profile("!production")
public class SwaggerConfig {
    @Bean
    public Docket adminApi() {
        return getDocket("admin-api", "/admin/**");
    }
    @Bean
    public Docket internalApi() {
        return getDocket("internal-api", "/internal/**");
    }
    @Bean
    public Docket frontApi() {
        return getDocket("front-api", "/front/**");
    }

    private Docket getDocket(String groupName, String pathPattern) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grapheople"))
                .paths(PathSelectors.ant(pathPattern))
                .build()
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true);

        if(0 == pathPattern.compareTo("/internal/**")){
            docket.globalOperationParameters(
                    newArrayList(new ParameterBuilder()
                            .name("X-INTERNAL-API-KEY")
                            .description("API Key")
                            .modelRef(new ModelRef("string"))
                            .parameterType("header")
                            .required(true)
                            .build()));
        }
        return docket;
    }
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Comarket API Document")
                .description("Comarket API based on Spring Boot")
                .version("1.0")
                .contact(new Contact("Pax", "", "pax.jee@kakaocorp.com"))
                .build();
    }
}
