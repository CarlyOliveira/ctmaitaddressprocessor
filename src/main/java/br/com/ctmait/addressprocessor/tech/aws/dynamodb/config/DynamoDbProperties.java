package br.com.ctmait.addressprocessor.tech.aws.dynamodb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties("dynamodb")
public class DynamoDbProperties {
    private String awsRegion = "sa-east-1";
}
