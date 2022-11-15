package br.com.ctmait.addressprocessor.tech.aws.sqs.publisher;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.State;
import br.com.ctmait.addressprocessor.tech.aws.sqs.mapper.AddressPayloadMapper;
import br.com.ctmait.addressprocessor.tech.infrastructure.pubsub.AddressPublisherMessage;
import com.amazonaws.SdkBaseException;
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
    private String queueNameInclusion;

    @Value("${queue.address-alteracao}")
    private String queueNameAlteration;

    @Value("${queue.address-exclusao}")
    private String queueNameExclusion;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public void send(Address address) throws AddressValidationException, AddressException {

        try {
            log.info("APMS-S-00 publisher address {} for aws sqs ", address);
            var payload = AddressPayloadMapper.INSTANCE.map(address);
            log.info("APMS-S-01 publisher message payload {} for aws sqs ", payload);
            var queueName = this.getQueueNameByEvent(address);
            queueMessagingTemplate.convertAndSend(queueName,payload);
            log.info("APMS-S-02 message payload {} published for aws sqs ", payload);
        }catch (NullPointerException nullPointerException){
            log.error("APMS-S-03 error {} publisher Address {} for aws sqs ", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (SdkBaseException sdkBaseException){
            log.error("APMS-S-04 error {} publisher Address {} for aws sqs ", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("APMS-S-05 error {} publisher Address {} for aws sqs ", exception, address);
            throw new AddressException(exception);
        }
    }

    private String getQueueNameByEvent(Address address){
        if(address.getState() == null || address.getState().equals(State.PUBLISHED)){
            throw new IllegalArgumentException("Address State is invalid");
        }
       return address.getState().equals(State.INCLUDED) ? queueNameInclusion : address.getState().equals(State.UPDATED) ? queueNameAlteration : queueNameExclusion;
    }
}
