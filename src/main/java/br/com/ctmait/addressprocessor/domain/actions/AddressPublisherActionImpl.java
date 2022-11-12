package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressPublisherAction;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.State;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Action;
import br.com.ctmait.addressprocessor.tech.infrastructure.pubsub.AddressPublisherMessage;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Action
public class AddressPublisherActionImpl implements AddressPublisherAction {

    private static final Logger log = LoggerFactory.getLogger(AddressPublisherActionImpl.class);

    private final AddressPublisherMessage addressPublisherMessage;
    private final AddressRepository addressRepository;

    public AddressPublisherActionImpl(AddressPublisherMessage addressPublisherMessage, AddressRepository addressRepository) {
        this.addressPublisherMessage = addressPublisherMessage;
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address address) throws AddressValidationException, AddressException {
        try{
            log.info("APAI-E-00 Publish Address {} ", address);
            address.visit(addressPublisherMessage::send);
            address.visit(this::addressPublisherMessageSucess);
            log.info("APAI-E-01 Address {} Published", address);
        }catch (AddressValidationException addressValidationException){
            log.error("APAI-E-02 Publish Address {} error ", address, addressValidationException);
            throw addressValidationException;
        }catch (AddressException addressException){
            log.error("APAI-E-03 Publish Address {} error ", address, addressException);
            throw addressException;
        }catch (Exception exception){
            log.error("APAI-E-04 Publish Address {} error ", address, exception);
            throw new AddressException(exception);
        }
    }

    private void addressPublisherMessageSucess(Address address){
        address.setState(State.PUBLISHED);
        addressRepository.update(address);
    }
}
