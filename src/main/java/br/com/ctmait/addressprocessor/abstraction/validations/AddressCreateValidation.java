package br.com.ctmait.addressprocessor.abstraction.validations;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressCreateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressCreateValidation {
    void execute(final Address address) throws AddressCreateValidationException, AddressException;
}
