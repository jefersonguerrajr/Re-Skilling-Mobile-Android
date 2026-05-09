package dev.jefersonguerrajr.contatos_api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Contatos API")
                    .description("API REST para gerenciamento de contatos")
                    .version("1.0.0")
            )
    }
}
