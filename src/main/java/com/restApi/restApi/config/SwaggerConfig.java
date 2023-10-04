
package com.restApi.restApi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

// 스웨거 참고 : https://resilient-923.tistory.com/414
//              https://colabear754.tistory.com/99
@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Awesome API Title")
                        .description("My User management REST API service")
                        .version("1.0")
                        .termsOfService("urn:tos")
                        .contact(new Contact()
                                .name("Kenneth Lee")
                                .url("http://www.joneconsulting.co.kr")
                                .email("edowon@joneconsulting.co.kr"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")));
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            // 작업 설정을 변경할 수 있습니다.
            return operation;
        };
    }
}
