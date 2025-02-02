package br.com.producer.comment_producer.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

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
    public AmazonSQSAsync amazonSQSAsync() {
        AWSCredentials credentials = new BasicAWSCredentials("your-access-key", "your-secret-key");

        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(configurationEndpoint())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration configurationEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration("https://localhost.localstack.cloud:4566/000000000000/sqs-rent-queue", Region.US_EAST_1.toString());
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync sqsClient) {
        return new QueueMessagingTemplate(sqsClient);
    }
}
