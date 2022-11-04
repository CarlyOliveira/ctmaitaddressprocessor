package br.com.ctmait.addressprocessor.tech.aws.sns.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

//@Configuration
//@Profile({"local"})
public class SnsSpringConfigurationLocal {

    private static final String SERVICE_ENDPOINT = "http://localhost:4566";
    private static final String ACCESS_KEY = "fakeAccessKeyId";
    private static final String SECRET_KEY = "fakeSecretAccessKey";
    private static String awsRegion = "sa-east-1";
    private static String AWS_TOPIC_ARN = "arn:aws:sns:sa-east-1:000000000000:ctmait-address-events";
    private static String TOPIC_NAME = "ctmait-address-events";


    @Bean
    public AmazonSNS amazonSNS() {
        var endpoint = new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, awsRegion);
        var credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        return AmazonSNSClientBuilder
                .standard()
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public AmazonSNSAsync amazonSNSAsync() {
        var endpoint = new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, awsRegion);
        var credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        return AmazonSNSAsyncClientBuilder.standard()
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate() {
        NotificationMessagingTemplate notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSNS());
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setSerializedPayloadClass(String.class);
        mappingJackson2MessageConverter.getObjectMapper().registerModule(new JavaTimeModule());
        notificationMessagingTemplate.setMessageConverter(mappingJackson2MessageConverter);
        notificationMessagingTemplate.setDefaultDestinationName(TOPIC_NAME);
        return notificationMessagingTemplate;
    }
}
