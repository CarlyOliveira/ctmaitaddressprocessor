package br.com.ctmait.addressprocessor.tech.aws.sns.publisher;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.pubsub.AddressPublisherMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Component
@RequiredArgsConstructor
public class AddressPublisherMessageSns implements AddressPublisherMessage {

    private final SnsClient snsClient;
    private final PublishRequest publishRequest;

    @Override
    public void send(Address address) {

        try {
            var request = publishRequest.toBuilder().message("Funcionou").build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.out.println(e.awsErrorDetails().errorMessage());
        }
    }
}
