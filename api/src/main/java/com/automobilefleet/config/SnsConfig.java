package com.automobilefleet.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnsConfig {


    @Value("${aws.security.accessKeyId}")
    private String accessKeyId;
    @Value("${aws.security.secretAccessKey}")
    private String secretAccessKey;

//    @Bean
//    public AmazonSNS amazonSNS() {
//        return AmazonSNSClientBuilder
//                .standard()
//                .withCredentials(credentials())
//                .withRegion(Regions.US_EAST_1)
//                .build();
//    }

    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:4566",
                        "us-east-1"))
                .build();
    }

    private AWSStaticCredentialsProvider credentials() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKeyId, secretAccessKey));
    }
}
