package br.com.ctmait.addressprocessor.tech.aws.dynamodb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
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
@Profile({"local"})
@RequiredArgsConstructor
@Getter
@Setter
public class DynamoDbConfigurationLocal {

    private final DynamoDbProperties dynamoDbProperties;
    private static final String SERVICE_ENDPOINT = "http://localhost4566";
    private static final String ACCESS_KEY = "fakeAccessKeyId";
    private static final String SECRET_KEY = "fakeSecretAccessKey";



    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        final AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT,dynamoDbProperties.getAwsRegion());
        final BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY );
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(final AmazonDynamoDB amazonDynamoDB){
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Bean
    public DynamoDB dynamoDB(final AmazonDynamoDB amazonDynamoDB){
        return new DynamoDB(amazonDynamoDB);
    }
}
