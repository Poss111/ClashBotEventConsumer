package com.poss.clash.bot.ws.consumer.configs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import com.poss.clash.bot.ws.consumer.configs.properties.AWSEndpointConfiguration;
import com.poss.clash.bot.ws.consumer.configs.properties.ClashBotEventStreamProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Configuration
@Slf4j
public class ClashBotConfig {

    private final ClashBotEventStreamProperties clashBotEventStreamProperties;
    private final AWSEndpointConfiguration awsEndpointConfiguration;

    @Bean
    WebClient webClient() {
        log.debug("Using url for Clash Bot Event stream {}...", clashBotEventStreamProperties.getUrl());
        return WebClient.create(clashBotEventStreamProperties.getUrl());
    }

    @Bean
    @Profile("local || k8s")
    AmazonKinesisAsync amazonKinesis(AWSCredentialsProvider awsCredentialsProvider) {
        log.info("Instantiating local stack Kinesis connection {}...", awsEndpointConfiguration);
        return AmazonKinesisAsyncClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        awsEndpointConfiguration.getUrl(),
                        awsEndpointConfiguration.getSigningRegion()
                ))
                .build();
    }

    @Bean
    @Profile("local || k8s")
    AmazonDynamoDBAsync amazonDynamoDBAsync(AWSCredentialsProvider awsCredentialsProvider) {
        log.info("Instantiating local stack DynamoDb connection {}...", awsEndpointConfiguration);
        return AmazonDynamoDBAsyncClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        awsEndpointConfiguration.getUrl(),
                        awsEndpointConfiguration.getSigningRegion()
                ))
                .build();
    }

}
