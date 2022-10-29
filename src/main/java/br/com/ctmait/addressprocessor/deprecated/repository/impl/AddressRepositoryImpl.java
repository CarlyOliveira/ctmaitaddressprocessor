package br.com.ctmait.addressprocessor.deprecated.repository.impl;

import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;
import br.com.ctmait.addressprocessor.deprecated.repository.AddressRepository;
import br.com.ctmait.addressprocessor.deprecated.repository.entity.AddressEntity;
import br.com.ctmait.addressprocessor.deprecated.repository.mapper.AddressEntityMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public void save(Address address) {

        try {
            address.setId(UUID.randomUUID());
            var addressEntity = AddressEntityMapper.INSTANCE.AddressToAddressEntity(address);
            addressEntity.setDataInclusao(LocalDate.now());
            DynamoDBTableMapper<AddressEntity, String, ?> dynamoDBTableMapper = dynamoDBMapper.newTableMapper(AddressEntity.class);
            dynamoDBTableMapper.saveIfNotExists(addressEntity);
        }catch (ConditionalCheckFailedException conditionalCheckFailedException){
//            save(address);
        }catch (Exception exception){

        }
    }
}
