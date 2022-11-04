package br.com.ctmait.addressprocessor.tech.aws.sns.publisher;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.pubsub.AddressPublisherMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Component
@RequiredArgsConstructor
public class AddressPublisherMessageSns implements AddressPublisherMessage {

    private final SnsClient snsClient;
    private final PublishRequest publishRequest;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Override
    public void send(Address address) {

        try {
            var request = publishRequest.toBuilder().message("Funcionou").build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.out.println(e.awsErrorDetails().errorMessage());
        }
        Message<String> addressMessage = new Message<String>() {
            @Override
            public String getPayload() {
                return "Testando";
            }

            @Override
            public MessageHeaders getHeaders() {
                Map<String, Object> headers = new HashMap<>();
                headers.put("requestId", UUID.randomUUID().toString());
                headers.put("timestamp", LocalDateTime.now().toString());
                return new MessageHeaders(headers);
            }
        };
        try {

//            notificationMessagingTemplate.setDefaultDestinationName("arn:aws:sns:sa-east-1:000000000000:ctmait-address-events");
//            notificationMessagingTemplate.send(addressMessage);
            notificationMessagingTemplate.sendNotification("ctmait-address-events", "testando", "ADDRESS-CREATE");
        }catch (Exception e){
            System.out.println(addressMessage.getHeaders());
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
}
