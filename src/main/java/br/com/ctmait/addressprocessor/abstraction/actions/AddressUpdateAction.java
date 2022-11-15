package br.com.ctmait.addressprocessor.abstraction.actions;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressUpdateAction {
    void execute(final Address address) throws AddressValidationException, AddressException;
}
