package br.com.ctmait.addressprocessor.tech.infrastructure.pubsub;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressPublisherMessage {
    void send(final Address address) throws AddressValidationException, AddressException;
}
