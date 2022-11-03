package br.com.ctmait.addressprocessor.domain.process;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressPublisherAction;
import br.com.ctmait.addressprocessor.abstraction.actions.AddressSaveAction;
import br.com.ctmait.addressprocessor.abstraction.process.AddressCreateProcess;
import br.com.ctmait.addressprocessor.abstraction.validations.AddressValidation;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Process
public class AddressCreateProcessImpl implements AddressCreateProcess {

    private static final Logger log = LoggerFactory.getLogger(AddressCreateProcessImpl.class);

    private final AddressValidation addressValidation;

    private final AddressSaveAction addressSaveAction;

    private final AddressPublisherAction addressPublisherAction;

    public AddressCreateProcessImpl(AddressValidation addressValidation, AddressSaveAction addressSaveAction, AddressPublisherAction addressPublisherAction) {
        this.addressValidation = addressValidation;
        this.addressSaveAction = addressSaveAction;
        this.addressPublisherAction = addressPublisherAction;
    }

    @Override
    public void execute(Address address) {

        log.info("ACPI-E-00", "Iniciando AddressCreateProcessImpl com Address", address);

        address.visit(addressValidation::execute);

        log.info("ACPI-E-01", "Address", address);

        address.visit(addressSaveAction::execute);

        address.visit(addressPublisherAction::execute);

        log.info("ACPI-E-02", "finalizando AddressCreateProcessImpl com Address", address);

    }
}
