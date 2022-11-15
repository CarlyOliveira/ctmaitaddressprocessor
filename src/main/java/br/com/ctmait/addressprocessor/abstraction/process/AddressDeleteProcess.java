package br.com.ctmait.addressprocessor.abstraction.process;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressDeleteValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressDeleteProcess {
    void execute(final Address address) throws AddressValidationException, AddressDeleteValidationException, AddressException;
}
