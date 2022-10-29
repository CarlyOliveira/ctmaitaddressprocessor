package br.com.ctmait.addressprocessor.abstraction.process;

import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressCreateProcess {
    void execute(final Address address);
}
