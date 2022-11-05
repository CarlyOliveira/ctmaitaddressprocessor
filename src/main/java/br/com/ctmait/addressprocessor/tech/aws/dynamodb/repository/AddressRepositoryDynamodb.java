package br.com.ctmait.addressprocessor.tech.aws.dynamodb.repository;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.entity.AddressEntity;
import br.com.ctmait.addressprocessor.tech.aws.dynamodb.mapper.AddressEntityMapper;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import com.amazonaws.SdkBaseException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryDynamodb implements AddressRepository {

    private static final Logger log = LoggerFactory.getLogger(AddressRepositoryDynamodb.class);

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public void insert(Address address) {
        log.info("ARD-S-00 Inserindo Address {}", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.saveIfNotExists(addressEntity);
            log.info("ARD-S-01 Address {} Inserido ", addressEntity);
        }catch (NullPointerException nullPointerException){
            log.error("ARD-S-02 Erro {} Inserindo Address {}", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            log.error("ARD-S-03 Erro {} Inserindo Address {}", conditionalCheckFailedException, address);
            throw new AddressException(conditionalCheckFailedException);
        }catch (SdkBaseException sdkBaseException){
            log.error("ARD-S-04 Erro {} Inserindo Address {}", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("ARD-S-05 Erro {} Inserindo Address {}", exception, address);
            throw new AddressException(exception);
        }
    }

    @Override
    public void update(Address address) {
        log.info("ARD-U-00 Atualizando Address {}", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.saveIfExists(addressEntity);
            log.info("ARD-U-01 Address {} Atualizado ", addressEntity);
        }catch (NullPointerException nullPointerException){
            log.error("ARD-U-02 Erro {} Atualizando Address {}", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            log.error("ARD-U-03 Erro {} Atualizando Address {}", conditionalCheckFailedException, address);
            throw new AddressException(conditionalCheckFailedException);
        }catch (SdkBaseException sdkBaseException){
            log.error("ARD-U-04 Erro {} Atualizando Address {}", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("ARD-U-05 Erro {} Atualizando Address {}", exception, address);
            throw new AddressException(exception);
        }
    }

    @Override
    public void delete(Address address) {
        log.info("ARD-D-00 Deletando Address {}", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            var addressEntity = AddressEntityMapper.INSTANCE.map(address);
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.deleteIfExists(addressEntity);
            log.info("ARD-D-01 Address {} Deletado ", addressEntity);
        }catch (NullPointerException nullPointerException){
            log.error("ARD-D-02 Erro {} Deletando Address {}", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
            log.error("ARD-D-03 Erro {} Deletando Address {}", conditionalCheckFailedException, address);
            throw new AddressException(conditionalCheckFailedException);
        }catch (SdkBaseException sdkBaseException){
            log.error("ARD-D-04 Erro {} Deletando Address {}", sdkBaseException, address);
            throw new AddressException(sdkBaseException);
        }catch (Exception exception){
            log.error("ARD-D-05 Erro {} Deletando Address {}", exception, address);
            throw new AddressException(exception);
        }
    }
}
