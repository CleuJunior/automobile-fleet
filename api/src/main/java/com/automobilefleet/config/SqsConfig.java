package com.automobilefleet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Value("${aws.queue.url}")
    private String queueUrl;

    @Value("${aws.security.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.security.secretAccessKey}")
    private String secretAccessKey;

    @Bean
    public SqsAsyncClient sqsAsyncClientLocalDev() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(queueUrl))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)
                ))
                .build();
    }
}
