package br.com.ctmait.addressprocessor.abstraction.process;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressCreateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressCreateProcess {
    void execute(final Address address) throws AddressValidationException, AddressCreateValidationException, AddressException;
}
