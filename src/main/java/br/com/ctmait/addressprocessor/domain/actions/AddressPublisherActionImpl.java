package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressPublisherAction;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Action;
import br.com.ctmait.addressprocessor.tech.infrastructure.pubsub.AddressPublisherMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Action
public class AddressPublisherActionImpl implements AddressPublisherAction {

    private static final Logger log = LoggerFactory.getLogger(AddressPublisherActionImpl.class);

    private final AddressPublisherMessage addressPublisherMessage;

    public AddressPublisherActionImpl(AddressPublisherMessage addressPublisherMessage) {
        this.addressPublisherMessage = addressPublisherMessage;
    }

    @Override
    public void execute(Address address) {
        log.info("APAI-E-00", "Publicando Address", address);
        address.visit(addressPublisherMessage::send);
        log.info("APAI-E-01", "Address Publicado", address);
    }
}
