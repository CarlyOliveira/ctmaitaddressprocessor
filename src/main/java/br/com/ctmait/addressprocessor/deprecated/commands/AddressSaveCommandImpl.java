package br.com.ctmait.addressprocessor.deprecated.commands;

import br.com.ctmait.addressprocessor.deprecated.domain.core.commands.AddressSaveCommand;
import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import br.com.ctmait.addressprocessor.deprecated.repository.AddressRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;


@Named
@Slf4j
public class AddressSaveCommandImpl implements AddressSaveCommand<Address> {

    private final AddressRepository addressRepository;

    @Inject
    public AddressSaveCommandImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address domainModel) {
        log.info("ASCI-E-00", "Salvando Address", domainModel);
        domainModel.accept(addressRepository::save);
        log.info("ASCI-E-00", "Address Salvo", domainModel);
    }
}
