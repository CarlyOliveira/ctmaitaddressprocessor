package br.com.ctmait.addressprocessor.deprecated.commands;

import br.com.ctmait.addressprocessor.deprecated.domain.core.commands.AddressValidateCommand;
import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
public class AddressValidateCommandImpl implements AddressValidateCommand<Address> {

    @Override
    public void execute(Address domainModel) {
        log.info("ASCI-E-00", "Validando Address", domainModel);
    }
}
