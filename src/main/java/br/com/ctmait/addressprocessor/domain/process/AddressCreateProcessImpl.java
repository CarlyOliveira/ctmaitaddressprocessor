package br.com.ctmait.addressprocessor.domain.process;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressSaveAction;
import br.com.ctmait.addressprocessor.abstraction.process.AddressCreateProcess;
import br.com.ctmait.addressprocessor.abstraction.validations.AddressValidation;
import br.com.ctmait.addressprocessor.domain.models.Address;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class AddressCreateProcessImpl implements AddressCreateProcess {

    private static final Logger log = LoggerFactory.getLogger(AddressCreateProcessImpl.class);

    private final AddressValidation addressValidation;

    private final AddressSaveAction addressSaveAction;

    @Inject
    public AddressCreateProcessImpl(AddressValidation addressValidation, AddressSaveAction addressSaveAction) {
        this.addressValidation = addressValidation;
        this.addressSaveAction = addressSaveAction;
    }

    @Override
    public void execute(Address address) {

        log.info("ACPI-E-00", "Iniciando AddressCreateUseCaseImpl com Address", address);

        address.visit(addressValidation::execute);

        log.info("ACPI-E-01", "Address", address);

        address.visit(addressSaveAction::execute);

        log.info("ACPI-E-02", "finalizando AddressCreateUseCaseImpl com Address", address);

    }
}