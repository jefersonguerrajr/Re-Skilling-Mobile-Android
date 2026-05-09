package dev.jefersonguerrajr.contatos_api.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StartupInfoConfig {

    private val log = LoggerFactory.getLogger(StartupInfoConfig::class.java)

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationReady() = log.info(
            "\n========================================\n" +
            "  Application started successfully!\n" +
            "  Swagger UI: http://localhost:8080/swagger\n" +
            "========================================"
        )

}
