package br.com.ctmait.addressprocessor.abstraction.actions;

import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressPublisherAction {
    void execute(final Address address);
}
