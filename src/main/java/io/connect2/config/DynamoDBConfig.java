package io.connect2.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
        (basePackages = "io.connect2.repository")
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {

        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(awsCredentialsProvider())
                .withEndpointConfiguration(dynamoDbEndpointConfiguration())
                .build();
    }

    private EndpointConfiguration dynamoDbEndpointConfiguration() {
        return new EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion);
    }

    private AWSCredentialsProvider awsCredentialsProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }
}
