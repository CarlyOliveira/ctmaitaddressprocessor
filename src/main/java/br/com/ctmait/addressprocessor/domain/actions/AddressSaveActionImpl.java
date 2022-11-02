package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressSaveAction;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Action;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Action
public class AddressSaveActionImpl implements AddressSaveAction {

    private static final Logger log = LoggerFactory.getLogger(AddressSaveActionImpl.class);

    private final AddressRepository addressRepository;

    public AddressSaveActionImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address address) {
        log.info("ASAI-E-00", "Salvando Address", address);
        address.visit(addressRepository::save);
        log.info("ASAI-E-01", "Address Salvo", address);
    }
}
