package br.com.ctmait.addressprocessor.tech.aws.dynamodb.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(DynamoDbProperties.class)
@Profile({"dev", "hom", "prod"})
@RequiredArgsConstructor
@Getter
@Setter
public class DynamoDbConfiguration {

    private final DynamoDbProperties dynamoDbProperties;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBClientBuilder
                .standard()
                .withClientConfiguration(new ClientConfiguration().withRetryPolicy(PredefinedRetryPolicies.getDynamoDBDefaultRetryPolicy()))
                .withRegion(dynamoDbProperties.getAwsRegion())
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }

    @Bean
    public DynamoDB dynamoDB(){
        return new DynamoDB(amazonDynamoDB());
    }
}
