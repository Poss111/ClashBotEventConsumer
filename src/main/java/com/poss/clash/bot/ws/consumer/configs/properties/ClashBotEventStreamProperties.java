package com.poss.clash.bot.ws.consumer.configs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("clash-bot.event-stream")
@Data
public class ClashBotEventStreamProperties {

    private String url;

}
