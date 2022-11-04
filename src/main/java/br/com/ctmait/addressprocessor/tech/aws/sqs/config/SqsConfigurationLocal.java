package br.com.ctmait.addressprocessor.tech.aws.sqs.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
@Profile({"local"})
public class SqsConfigurationLocal {

    @Value("${aws.region.static}")
    private String region;
    @Value("${aws.credentials.access-key}")
    private String accessKey;
    @Value("${aws.credentials.secret-key}")
    private String secretKey;
    @Value("${aws.sqs.endpoint}")
    private String endpoint;

    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        var credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        var queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync());
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setSerializedPayloadClass(String.class);
        mappingJackson2MessageConverter.getObjectMapper().registerModule(new JavaTimeModule());
        queueMessagingTemplate.setMessageConverter(mappingJackson2MessageConverter);
//        queueMessagingTemplate.setDefaultDestinationName(AWS_QUEUE_NAME);
        return queueMessagingTemplate;
    }

}
