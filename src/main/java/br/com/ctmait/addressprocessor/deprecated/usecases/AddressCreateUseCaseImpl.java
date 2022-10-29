package br.com.ctmait.addressprocessor.deprecated.usecases;

import br.com.ctmait.addressprocessor.deprecated.domain.core.commands.AddressSaveCommand;
import br.com.ctmait.addressprocessor.deprecated.domain.core.commands.AddressValidateCommand;
import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import br.com.ctmait.addressprocessor.deprecated.domain.core.usecases.AddressCreateUseCase;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
@RequiredArgsConstructor
public class AddressCreateUseCaseImpl implements AddressCreateUseCase<Address> {

    @Inject
    private final AddressValidateCommand<Address> addressAddressValidateCommand;
    @Inject
    private final AddressSaveCommand<Address> addressAddressSaveCommand;

    @Override
    public Address execute(Address domainModel) {

        log.info("ACUCI-E-00", "Iniciando AddressCreateUseCaseImpl com Address", domainModel);

        domainModel.accept(addressAddressValidateCommand::execute);

        log.info("ACUCI-E-01", "Address", domainModel);

        domainModel.accept(addressAddressSaveCommand::execute);

        log.info("ACUCI-E-02", "finalizando AddressCreateUseCaseImpl com Address", domainModel);

        return domainModel;
    }
}
