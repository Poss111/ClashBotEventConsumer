package com.poss.clash.bot.ws.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
class ClashBotWsSpringBootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClashBotWsSpringBootConsumerApplication.class, args);
    }

}
