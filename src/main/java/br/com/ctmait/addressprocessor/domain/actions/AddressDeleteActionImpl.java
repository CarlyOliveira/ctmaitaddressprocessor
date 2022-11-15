package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressDeleteAction;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressDeleteValidationException;
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
public class AddressDeleteActionImpl implements AddressDeleteAction {

    private static final Logger log = LoggerFactory.getLogger(AddressDeleteActionImpl.class);

    private final AddressRepository addressRepository;

    public AddressDeleteActionImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address address) throws AddressValidationException, AddressDeleteValidationException, AddressException{

        try {

            Objects.requireNonNull(address, "address cannot null");

            log.info("ADAI-E-00 deleting address {}", address);
            address.visit(this::loadAddress);
            address.visit(addressRepository::delete);
            log.info("ADAI-E-01 deleted address {}", address);
        }catch (NullPointerException nullPointerException){
            log.error("ADAI-S-02 error {} deleting address {}", nullPointerException, address);
            throw new AddressDeleteValidationException(nullPointerException);
        }catch (AddressValidationException addressValidationException){
            log.error("ADAI-S-03 error {} deleting address {}", addressValidationException, address);
            throw addressValidationException;
        }catch (AddressException addressException){
            log.error("ADAI-S-04 Error {} deleting address {}", addressException, address);
            throw addressException;
        }catch (Exception exception){
            log.error("ADAI-S-05 error {} deleting Address {}", exception, address);
            throw new AddressException(exception);
        }
    }

    private void loadAddress(Address address){
        log.info("ADAI-LA-00 load address {}", address);
        var addressLoaded = addressRepository.getById(address.getId());
        address.setState(State.EXCLUDED);
        address.setBairro(addressLoaded.getBairro());
        address.setCep(addressLoaded.getCep());
        address.setCidade(addressLoaded.getCidade());
        address.setComplemento(addressLoaded.getComplemento());
        address.setLogradouro(addressLoaded.getLogradouro());
        address.setNumero(addressLoaded.getNumero());
        address.setUf(addressLoaded.getUf());
        address.setProvider(addressLoaded.getProvider());
        log.info("ADAI-LA-01 loaded address {}", address);
    }
}
