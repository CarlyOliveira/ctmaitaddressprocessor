package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressCreateAction;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.State;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Action;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


@Action
public class AddressCreateActionImpl implements AddressCreateAction {

    private static final Logger log = LoggerFactory.getLogger(AddressCreateActionImpl.class);

    private final AddressRepository addressRepository;

    public AddressCreateActionImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address address) {

        try {

            Objects.requireNonNull(address, "address cannot null");

            log.info("ACAI-E-00 Criando Address {}", address);

            address.visit(this::setState);
//            address.visit(this::setId);
            address.visit(addressRepository::insert);

            log.info("ACAI-E-01 Address Criado {}", address);
        }catch (NullPointerException nullPointerException){
            log.error("ACAI-S-02 Erro {} Criando Address {}", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (AddressValidationException addressValidationException){
            log.error("ACAI-S-03 Erro {} Criando Address {}", addressValidationException, address);
            throw addressValidationException;
        }catch (AddressException addressException){
            log.error("ACAI-S-04 Erro {} Criando Address {}", addressException, address);
            throw addressException;
        }catch (Exception exception){
            log.error("ACAI-S-05 Erro {} Criando Address {}", exception, address);
            throw new AddressException(exception);
        }
    }

    private void setState(Address address){
        log.info("ACAI-SS-00 Setando State em Address {}", address);
        address.setState(State.READY);
        log.info("ACAI-SS-01 State setado em Address {}", address);
    }

//    private void setId(Address address){
//        log.info("ACAI-SI-00 Setando State em Address {}", address);
//        address.setId(UUID.randomUUID().toString());
//        log.info("ACAI-SI-01 State setado em Address {}", address);
//    }
}
