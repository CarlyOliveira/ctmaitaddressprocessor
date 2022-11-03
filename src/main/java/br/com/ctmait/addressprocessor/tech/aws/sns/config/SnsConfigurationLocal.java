package br.com.ctmait.addressprocessor.tech.aws.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Configuration
@Profile({"local"})
public class SnsConfigurationLocal {

    private static final String SERVICE_ENDPOINT = "http://localhost:8001";
    private static final String ACCESS_KEY = "fakeAccessKeyId";
    private static final String SECRET_KEY = "fakeSecretAccessKey";
    private static String awsRegion = "sa-east-1";
    private static String AWS_TOPIC_ARN = "arn:aws:sns:sa-east-1:000000000000:ctmait-address-events";



    @Bean
    public SnsClient snsClient(){
        final AwsBasicCredentials credentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY );
        return SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Bean
    public PublishRequest publishRequest(){
        return PublishRequest.builder()
                .message("")
                .topicArn(AWS_TOPIC_ARN)
                .build();
    }
}
