package br.com.ctmait.addressprocessor.abstraction.actions;

import br.com.ctmait.addressprocessor.domain.models.Address;

@FunctionalInterface
public interface AddressSaveAction {
    void execute(final Address address);
}
