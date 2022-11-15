package br.com.ctmait.addressprocessor.tech.aws.dynamodb.repository;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.entity.AddressEntity;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.mapper.AddressEntityMapper;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import br.com.ctmait.addressprocessor.tech.rest.mapper.AddressMapper;
import com.amazonaws.SdkBaseException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryDynamodb implements AddressRepository {

    private static final Logger log = LoggerFactory.getLogger(AddressRepositoryDynamodb.class);

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public void insert(Address address) throws AddressValidationException, AddressException{
        log.info("ARD-S-00 insert address {}", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.saveIfNotExists(addressEntity);
            log.info("ARD-S-01 inserted addressEntity {} ", addressEntity);
        }catch (NullPointerException nullPointerException){
            log.error("ARD-S-02 error {} inserting address {} ", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            log.error("ARD-S-03 error {} inserting address {} ", conditionalCheckFailedException, address);
            throw new AddressException(conditionalCheckFailedException);
        }catch (SdkBaseException sdkBaseException){
            log.error("ARD-S-04 error {} inserting address {} ", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("ARD-S-05 error {} inserting address {} ", exception, address);
            throw new AddressException(exception);
        }
    }

    @Override
    public void update(Address address) throws AddressValidationException, AddressException{
        log.info("ARD-U-00 update address {}", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.saveIfExists(addressEntity);
            log.info("ARD-U-01 updated addressEntity {} ", addressEntity);
        }catch (NullPointerException nullPointerException){
            log.error("ARD-U-02 error {} updating address {} ", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            log.error("ARD-U-03 error {} updating address {} ", conditionalCheckFailedException, address);
            throw new AddressException(conditionalCheckFailedException);
        }catch (SdkBaseException sdkBaseException){
            log.error("ARD-U-04 error {} updating address {} ", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("ARD-U-05 error {} updating address {} ", exception, address);
            throw new AddressException(exception);
        }
    }

    @Override
    public void delete(Address address) throws AddressValidationException, AddressException{
        log.info("ARD-D-00 delete address {}", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.deleteIfExists(addressEntity);
            log.info("ARD-D-01 deleted address {} ", addressEntity);
        }catch (NullPointerException nullPointerException){
            log.error("ARD-D-02 error {} deleting address {} ", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            log.error("ARD-D-03 error {} deleting address {} ", conditionalCheckFailedException, address);
            throw new AddressException(conditionalCheckFailedException);
        }catch (SdkBaseException sdkBaseException){
            log.error("ARD-D-04 error {} deleting address {} ", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("ARD-D-05 error {} deleting address {} ", exception, address);
            throw new AddressException(exception);
        }
    }

    @Override
    public Address getById(String addressId) throws AddressValidationException, AddressException {
        log.info("ARD-G-00 get address by id {}", addressId);
        try {
            var addressEntity = dynamoDBMapper.load(AddressEntity.class, addressId);
            var address = AddressEntityMapper.INSTANCE.map(addressEntity);
            log.info("ARD-G-01 get return address {}", address);
            return address;
        }catch (Exception e){
            log.error("ARD-G-02 error {} get address by id {} ", e, addressId);
            throw new AddressException(e);
        }
    }
}
