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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "hom", "prod"})
@RequiredArgsConstructor
@Getter
@Setter
public class DynamoDbConfiguration {

    @Value("${aws.region.static}")
    private String awsRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBClientBuilder
                .standard()
                .withClientConfiguration(new ClientConfiguration().withRetryPolicy(PredefinedRetryPolicies.getDynamoDBDefaultRetryPolicy()))
                .withRegion(awsRegion)
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
