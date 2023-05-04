package com.poss.clash.bot.ws.consumer.configs;

import com.poss.clash.bot.openapi.model.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;

@Configuration
@Slf4j
@AllArgsConstructor
public class EventConsumer {

    private final WebClient webClient;

    @Bean
    Consumer<Event> teamEvents() {
        return str -> {
            log.trace("Event: {}", str);
            String errorResponse = webClient
                    .post()
                    .uri("/invoke")
                    .bodyValue(str)
                    .exchangeToMono(response -> {
                        if (response.statusCode().equals(HttpStatus.OK)) {
                            return response.bodyToMono(String.class);
                        } else if (response.statusCode().is4xxClientError()) {
                            return Mono.just("Error response");
                        } else {
                            return response.createException()
                                    .flatMap(Mono::error);
                        }
                    })
                    .log()
                    .block(Duration.of(5, ChronoUnit.SECONDS));
        };
    }

}
