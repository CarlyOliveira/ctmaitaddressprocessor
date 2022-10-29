package br.com.ctmait.addressprocessor.abstraction.validations;

import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressValidation {
    void execute(final Address address);
}
