package br.com.ctmait.addressprocessor.tech.aws.sqs.publisher;

import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.aws.sqs.mapper.AddressPayloadPublisherMapper;
import br.com.ctmait.addressprocessor.tech.infrastructure.pubsub.AddressPublisherMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressPublisherMessageSqs implements AddressPublisherMessage {

    private static final Logger log = LoggerFactory.getLogger(AddressPublisherMessageSqs.class);

    @Value("${queue.address-inclusao}")
    private String queueName;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public void send(Address address) {

        try {
            log.info("APMS-S-00 Address {} for publisher", address);

            var payload = AddressPayloadPublisherMapper.INSTANCE.map(address);

            log.info("APMS-S-00 Pub message payload {}", payload);

            queueMessagingTemplate.convertAndSend(queueName,payload);

            log.info("APMS-S-01 Message payload {} published ", payload);
        }catch (Exception e){
            //TODO trabalhar o state machine em caso de erro -> MOVER PARA NOT_READY
            log.error("APMS-S-02 Error sending address {}", address, e);
        }
    }
}
